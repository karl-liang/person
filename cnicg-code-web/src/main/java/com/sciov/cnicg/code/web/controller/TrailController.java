package com.sciov.cnicg.code.web.controller;

import com.github.pagehelper.util.StringUtil;
import com.sciov.cnicg.code.module.response.ResponseData;
import com.sciov.cnicg.code.module.vo.TrailVo;
import com.sciov.cnicg.code.service.ITrailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/trail")
@Api(tags = "位置跟踪管理")
public class TrailController {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;

    private static final Logger logger = LoggerFactory.getLogger(TrailController.class);;

    @Autowired
    private ITrailService trailServiceImpl;

    @ApiOperation(value = "查询位置跟踪")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<TrailVo> getTrail(@PathVariable int id) {
         return new ResponseData<>(trailServiceImpl.getTrail(id));
    }

    @ApiOperation(value = "新增位置跟踪")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<Void> addTrail(@Valid @RequestBody TrailVo vo) {
        trailServiceImpl.addTrail(vo);
        return new ResponseData(200, "添加成功");
    }

    @ApiOperation(value = "删除位置跟踪")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<Void> deleteTrail(@PathVariable int id) {
        trailServiceImpl.deleteTrail(id);
        return new ResponseData(200, "删除成功");
    }

    @ApiOperation(value = "更新位置跟踪")
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<Void> updateTrail(@Valid @RequestBody TrailVo vo) {
        trailServiceImpl.updateTrail(vo);
        return new ResponseData(200, "更新成功");
    }
    
    
    @ApiOperation(value = "显示轨迹图")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public void getTrail(HttpServletRequest request, HttpServletResponse response, @RequestParam String person,
    		@RequestParam(defaultValue = "12") int zoom, @RequestParam( required = false) String startDateStr) throws Exception {
    	Date startDate;
    	if(StringUtil.isEmpty(startDateStr)) {
    		Calendar calendar = Calendar.getInstance();
    		calendar.set(Calendar.HOUR_OF_DAY, 0);
    		calendar.set(Calendar.MINUTE,0);
    		calendar.set(Calendar.SECOND,1);
    		startDate = calendar.getTime();
    	}else {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
    		startDate = sdf.parse(startDateStr);
    	}
    	trailServiceImpl.getTrail(request, response, person,zoom,startDate);
    }

    @ApiOperation("位置跟踪列表")
	@ApiImplicitParams({
	    @ApiImplicitParam(value = "id型查询条件", name = "condtionId", dataType = "int", paramType = "query", required = true),
	    @ApiImplicitParam(value = "字符串型查询条件", name = "conditionStr", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(value = "页码", name = "pageNum", dataType = "int", paramType = "query", defaultValue = "1"),
	    @ApiImplicitParam(value = "显示数量", name = "pageSize", dataType = "int", paramType = "query", defaultValue = "100000")
	})
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<List<TrailVo>> findTrailList(@RequestParam(defaultValue = "") String conditionStr, @RequestParam(required = false) String startDateStr, @RequestParam(required = false) String endDateStr, @RequestParam(defaultValue = "0") int condtionId, int pageNum, int pageSize) throws ParseException {
        Date startDate = null;
        if(!StringUtil.isEmpty(startDateStr)) {
			startDate = format.parse(startDateStr);
    	}
        Date endDate = null;
        if(!StringUtil.isEmpty(endDateStr)) {
		endDate = format.parse(endDateStr);
    	}
        TrailVo trailVo= new TrailVo();
        return trailServiceImpl.findTrailList(trailVo,startDate,endDate,pageNum,pageSize);
    }
}