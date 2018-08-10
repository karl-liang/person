package com.sciov.cnicg.code.web.controller;

import com.sciov.cnicg.code.module.response.ResponseData;
import com.sciov.cnicg.code.module.vo.RecordEventVo;
import com.sciov.cnicg.code.web.demo.SimpleEvent;
import com.sciov.cnicg.code.web.demo.geofencing.GeofencingService;

import cn.cnicg.code.recordengine.base.RecordEngineService;
import cn.cnicg.code.recordengine.base.StateEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/code")
@Api(tags = "码管理")
public class CodeController {
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;

	private static final Logger logger = LoggerFactory.getLogger(CodeController.class);;

	@Autowired
	private RecordEngineService recordEnginService;

	@Autowired
	private GeofencingService geofencingServiceImpl;

	@ApiOperation(value = "扫码记录")
    @RequestMapping(value = "/addCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<Void> addCode(@Valid @RequestBody RecordEventVo vo) {
    	StateEntity<String> entity = new StateEntity<String>(vo.getCode());
    	SimpleEvent event = new SimpleEvent( vo.getLocation(), new Date(),
				entity, null, null, vo.getAction(), vo.getRemark());
    	
    	recordEnginService.triggerRecord(entity, event);
        return new ResponseData(200, "添加成功");
    }

	// @ApiOperation(value = "创建围栏")
	// @RequestMapping(value = "/makeGeofencing", method = RequestMethod.POST,
	// produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	// public ResponseData<Void> makeGeofencing(@Valid @RequestParam String adcode)
	// {
	// String result = geofencingServiceImpl.makeGeoFencing(adcode);
	// return new ResponseData(200, result);
	// }

	@ApiOperation(value = "检查扫码位置是否在围栏内")
    @RequestMapping(value = "/isInGeofencing", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<Void> isInGeofencing(@Valid @RequestParam String location,@Valid @RequestParam String adcode) {
    	String result = geofencingServiceImpl.inArea(adcode,location);
        return new ResponseData(200, result);
    }
}