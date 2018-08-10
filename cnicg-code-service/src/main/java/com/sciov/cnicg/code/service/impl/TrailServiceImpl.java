package com.sciov.cnicg.code.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.net.MediaType;
import com.sciov.cnicg.code.module.bean.Trail;
import com.sciov.cnicg.code.module.bean.TrailExample;
import com.sciov.cnicg.code.module.response.ResponseData;
import com.sciov.cnicg.code.module.vo.TrailVo;
import com.sciov.cnicg.code.persist.mapper.TrailMapper;
import com.sciov.cnicg.code.service.ITrailService;

import cn.cnicg.code.recordengine.amap.AmapService;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
public class TrailServiceImpl implements ITrailService {
    @Autowired
    private TrailMapper trailMapper;
    
    @Autowired
    private AmapService amapService;

    private static final Logger logger = LoggerFactory.getLogger(TrailServiceImpl.class);;

    public TrailVo getTrail(int id) {
        return toVo(trailMapper.selectByPrimaryKey(id));
    }

    public int addTrail(TrailVo vo) {
        Trail trail= new Trail();
        trail.setGmtCreate(new Date());
        BeanUtils.copyProperties(vo, trail);
        return trailMapper.insert(trail);
        
    }

    public int deleteTrail(int id) {
        return trailMapper.deleteByPrimaryKey(id);
    }

    public int updateTrail(TrailVo vo) {
        Trail trail= trailMapper.selectByPrimaryKey(vo.getId());
        if(trail == null) {
			return 0;
		}
        BeanUtils.copyProperties(vo, trail);
        return trailMapper.updateByPrimaryKey(trail);
    }

    public ResponseData<List<TrailVo>> findTrailList(TrailVo vo, Date startDate, Date endDate, int pageNum, int pageSize) {
        TrailExample example = new TrailExample();
        TrailExample.Criteria criteria = example.createCriteria();
        Integer id = vo.getId();
		if (0 !=id) {
			criteria.andIdEqualTo(id);
		}
        String person = vo.getPerson();
		if (!StringUtils.isEmpty(person)) {
			criteria.andPersonLike("%person%");
		}
        String point = vo.getPoint();
		if (!StringUtils.isEmpty(point)) {
			criteria.andPointLike("%point%");
		}
        String location = vo.getLocation();
		if (!StringUtils.isEmpty(location)) {
			criteria.andLocationLike("%location%");
		}
        if(startDate != null) {
			criteria.andGmtCreateGreaterThanOrEqualTo(startDate);
		}
		if(endDate != null) {
			criteria.andGmtCreateLessThanOrEqualTo(endDate);
		}
        Page page = PageHelper.startPage(pageNum, pageSize);
		List<Trail> entityList = trailMapper.selectByExample(example);
        List<TrailVo> voList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(entityList)) {
			for (Trail entity : entityList) {
				voList.add(toVo(entity));
			}
		}
        //根据需要设置分页信息
        //PageInfo pageInfo = new PageInfo(pageNum, page.getPageSize(), page.getTotal());
		return new ResponseData<>(voList);
    }

    public TrailVo toVo(Trail entity) {
        TrailVo trailVo= new TrailVo();
        BeanUtils.copyProperties(entity, trailVo);
        return trailVo;
    }
	// 根据str,font的样式以及输出文件目录
	public static void createImage(String str, Font font, OutputStream outFile,
			int width, int height) throws Exception {
		// 创建图片
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();
		g.setClip(0, 0, width, height);
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);// 先用黑色填充整张图片,也就是背景
		g.setColor(Color.red);// 在换成黑色
		g.setFont(font);// 设置画笔字体
		/** 用于获得垂直居中y */
		Rectangle clip = g.getClipBounds();
		FontMetrics fm = g.getFontMetrics(font);
		int ascent = fm.getAscent();
		int descent = fm.getDescent();
		int y = (clip.height - (ascent + descent)) / 2 + ascent;
		for (int i = 0; i < 6; i++) {// 256 340 0 680
			g.drawString(str, i * 680, y);// 画出字符串
		}
		g.dispose();
		ImageIO.write(image, "png", outFile);// 输出png图片
	}
	@Override
	public void getTrail(HttpServletRequest request, HttpServletResponse response, String person, int zoom, Date startDate) throws Exception {
		 TrailExample example = new TrailExample();
	     TrailExample.Criteria criteria = example.createCriteria();
	     criteria.andPersonEqualTo(person);
	     criteria.andGmtCreateGreaterThan(startDate);
	     example.setOrderByClause("gmt_create desc");
	     List<Trail> entityList = trailMapper.selectByExample(example);
	     if(entityList.size() == 0) {
	    	 createImage("当前查询条件下没有位置数据",new Font("宋体",Font.BOLD,20),response.getOutputStream(),280,100);
	    	 response.setContentType("image/png;charset=UTF-8");
	    	 response.flushBuffer();
	    	 return;
	     }
	     StringBuilder sb = new StringBuilder();
	     if(entityList.size() < 10) {
	    	 for(Trail trail:entityList) {
	    		 sb.append(trail.getPoint()+";");
	    	 }
	     }else {
	    	 int size = entityList.size();
	    	 int indexC = size / 10;
	    	 for(int i=0;i<entityList.size();i+=indexC) {
	    		 Trail trail = entityList.get(i);
	    		 sb.append(trail.getPoint()+";");
	    	 }
	     }
	     sb.setLength(sb.length()-1);
	     
	     byte[] result = amapService.mark(sb.toString(),zoom);
	     response.setContentType("image/png;charset=UTF-8");
	     response.getOutputStream().write(result);
	     response.flushBuffer();
	     
//	     116.37359,39.92437;116.47359,39.92437
	}
}