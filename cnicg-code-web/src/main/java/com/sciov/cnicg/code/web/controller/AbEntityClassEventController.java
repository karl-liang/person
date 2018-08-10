package com.sciov.cnicg.code.web.controller;

import com.github.pagehelper.util.StringUtil;
import com.sciov.cnicg.code.module.response.ResponseData;
import com.sciov.cnicg.code.module.vo.AbEntityClassEventVo;
import com.sciov.cnicg.code.service.IAbEntityClassEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
@RequestMapping("/v1/abentityclassevent")
@Api(tags = "课程事件管理")
public class AbEntityClassEventController {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;

    private static final Logger logger = LoggerFactory.getLogger(AbEntityClassEventController.class);;

    @Autowired
    private IAbEntityClassEventService abEntityClassEventServiceImpl;

    @ApiOperation(value = "查询课程事件")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<AbEntityClassEventVo> getAbEntityClassEvent(@PathVariable int id) {
         return new ResponseData<>(abEntityClassEventServiceImpl.getAbEntityClassEvent(id));
    }

    @ApiOperation(value = "新增课程事件")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<Void> addAbEntityClassEvent(@Valid @RequestBody AbEntityClassEventVo vo) {
        abEntityClassEventServiceImpl.addAbEntityClassEvent(vo);
        return new ResponseData(200, "添加成功");
    }

    @ApiOperation(value = "删除课程事件")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<Void> deleteAbEntityClassEvent(@PathVariable int id) {
        abEntityClassEventServiceImpl.deleteAbEntityClassEvent(id);
        return new ResponseData(200, "删除成功");
    }

    @ApiOperation(value = "更新课程事件")
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<Void> updateAbEntityClassEvent(@Valid @RequestBody AbEntityClassEventVo vo) {
        abEntityClassEventServiceImpl.updateAbEntityClassEvent(vo);
        return new ResponseData(200, "更新成功");
    }

    @ApiOperation("课程事件列表")
	@ApiImplicitParams({
	    @ApiImplicitParam(value = "id型查询条件", name = "condtionId", dataType = "int", paramType = "query", required = true),
	    @ApiImplicitParam(value = "字符串型查询条件", name = "conditionStr", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(value = "页码", name = "pageNum", dataType = "int", paramType = "query", defaultValue = "1"),
	    @ApiImplicitParam(value = "显示数量", name = "pageSize", dataType = "int", paramType = "query", defaultValue = "100000")
	})
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<List<AbEntityClassEventVo>> findAbEntityClassEventList(@RequestParam(defaultValue = "") String conditionStr, @RequestParam(required = false) String startDateStr, @RequestParam(required = false) String endDateStr, @RequestParam(defaultValue = "0") int condtionId, int pageNum, int pageSize) throws ParseException {
        Date startDate = null;
        if(!StringUtil.isEmpty(startDateStr)) {
			startDate = format.parse(startDateStr);
    	}
        Date endDate = null;
        if(!StringUtil.isEmpty(endDateStr)) {
		endDate = format.parse(endDateStr);
    	}
        AbEntityClassEventVo abEntityClassEventVo= new AbEntityClassEventVo();
        return abEntityClassEventServiceImpl.findAbEntityClassEventList(abEntityClassEventVo,startDate,endDate,pageNum,pageSize);
    }
}