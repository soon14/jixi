/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.pig4cloud.pigx.api.application.controller;

import com.alibaba.nacos.client.utils.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.api.application.entity.DmAlarminfo;
import com.pig4cloud.pigx.api.application.service.DmAlarminfoService;
import com.pig4cloud.pigx.api.application.vo.DmAlarminfoVo;
import com.pig4cloud.pigx.api.application.vo.GroupDataVo;
import com.pig4cloud.pigx.api.common.Constant;
import com.pig4cloud.pigx.api.device.service.DmDeviceService;
import com.pig4cloud.pigx.api.device.vo.DmDeviceVO;
import com.pig4cloud.pigx.api.user.entity.BsNetworkingUnit;
import com.pig4cloud.pigx.api.user.service.BsNetworkingUnitService;
import com.pig4cloud.pigx.api.user.service.SysDictItemService;
import com.pig4cloud.pigx.api.user.service.SysUserService;
import com.pig4cloud.pigx.api.user.vo.NetworkingUnitVo;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.common.security.annotation.Inner;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import com.alibaba.fastjson.JSONArray;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 事件上送数据
 *
 * @author pigx code generator
 * @date 2019-08-08 11:37:42
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dmalarminfo")
@Api(value = "dmalarminfo", tags = "dmalarminfo管理")
public class DmAlarminfoController {

    private final DmAlarminfoService dmAlarminfoService;

    private final SysDictItemService sysDictItemService;

    private final DmDeviceService dmDeviceService;

    private final SimpMessagingTemplate messagingTemplate;

    private final SysUserService sysUserService;

    private final BsNetworkingUnitService bsNetworkingUnitService;

    /**
     * 查询报警记录
     *
     * @param page
     * @param vo
     * @return
     */
    @GetMapping("/getDmAlarmRecord")
    public R getDmAlarmRecord(Page page, DmAlarminfoVo vo) {
        try {
            //查询报警类型数据字典
            Map<String, String> m = sysDictItemService.getItemMap("alarm_type");
            //查询处理结果数据字典
            Map<String, String> mRes = sysDictItemService.getItemMap("handle_result");
            //查询产品类别数据字典
            Map<String, String> mp = sysDictItemService.getItemMap("product_category");
            List<DmAlarminfoVo> list = dmAlarminfoService.getDmAlarmRecord(page, vo, sysUserService.getUserLoingUnitIds());
            if (list != null && list.size() > 0) {
                for (DmAlarminfoVo _vo : list) {
                    if (_vo == null) continue;
                    _vo.setAlarmTypeName(m.get(_vo.getAlarmTypeId() + ""));
                    _vo.setHandleResultName(mRes.get(_vo.getHandleResult() + ""));
                    _vo.setTypeName(mp.get(_vo.getTypeId()));
                }
            }
            page.setRecords(list);
        } catch (Exception e) {
            return new R<>(e);
        }
        return new R<>(page);
    }

    /**
     * 报警处理详情
     *
     * @param alarmId
     * @return
     */
    @GetMapping("/getDmAlarmDetail")
    public R getDmAlarmDetail(String alarmId) {
        DmAlarminfoVo vo = null;
        try {
            //查询报警类型数据字典
            Map<String, String> m = sysDictItemService.getItemMap("alarm_type");
            //查询产品类别数据字典
            Map<String, String> mp = sysDictItemService.getItemMap("product_category");

            vo = dmAlarminfoService.getDmAlarmDetail(alarmId);
            DmDeviceVO deviceVo = dmDeviceService.getDeviceInfoByCode(vo.getDeviceCode());
            List<Map<String, String>> list = dmDeviceService.getVideoAddressByDeviceId(deviceVo.getId());
            if (list != null && list.size() > 0) {
                Map<String, String> map = list.get(0);
                if (map != null) {
                    vo.setVideoDeviceCode(map.get("deviceCode"));
                }
            }
            vo.setAlarmTypeName(m.get(vo.getAlarmTypeId() + ""));
            vo.setTypeName(mp.get(vo.getTypeId()));
        } catch (Exception e) {
            return new R<>(e);
        }
        return new R<>(vo);
    }

    /**
     * 报警处理
     *
     * @param info
     * @return
     */
    @PostMapping("/alarmHandle")
    public R alarmHandle(@RequestBody DmAlarminfo info) {
        try {
            dmAlarminfoService.alarmHandle(info);
            if(info.getHandleResult() == 4){
                messagingTemplate.convertAndSend("/topic/pushAlarmInfo", info);
            }
        } catch (Exception e) {
            return new R<>(e);
        }
        return new R<>(true);
    }

    /**
     * 推送报警数据
     *
     * @param dmAlarminfo
     * @return
     */
    @PostMapping("/pushAlarmInfo")
    @Inner(value = false)
    public R pushAlarmInfo(@RequestBody DmAlarminfo dmAlarminfo) {
        try {
            DmDeviceVO vo = dmDeviceService.getDeviceInfoByCode(dmAlarminfo.getDeviceCode());
            vo.setVideoList(dmDeviceService.getVideoAddressByDeviceId(vo.getId()));
            //查询联网单位的地址
            String unitAdress = bsNetworkingUnitService.getUnitById(vo.getNetworkUnitId()).getAddress();
            vo.setUnitAdress(unitAdress);
            vo.setAlarmReason(dmAlarminfo.getEventDesc());
            vo.setAlarmTime(dmAlarminfo.getAlarmTime());
            String alarmInfo = JSONArray.toJSONString(vo);
            messagingTemplate.convertAndSend("/topic/pushAlarmInfo", alarmInfo);
        } catch (Exception e) {
            return new R<>(e);
        }
        return new R<>(true);
    }

    /**
     * 推送真实警情
     *
     * @param dmAlarminfo
     * @return
     */
    @PostMapping("/pushRealAlarmInfo")
    public R pushRealAlarmInfo(@RequestBody DmAlarminfo dmAlarminfo) {
        try {
            DmDeviceVO vo = dmDeviceService.getDeviceInfoByCode(dmAlarminfo.getDeviceCode());
            vo.setVideoList(dmDeviceService.getVideoAddressByDeviceId(vo.getId()));
            //查询联网单位的地址
            NetworkingUnitVo unitVo = bsNetworkingUnitService.getUnitById(vo.getNetworkUnitId());
            vo.setContacts(unitVo.getContacts());
            vo.setTelephone(unitVo.getTelephone());
            vo.setUnitAdress(unitVo.getAddress());
            vo.setAlarmReason(dmAlarminfo.getEventDesc());
            vo.setAlarmTime(dmAlarminfo.getAlarmTime());
            String alarmInfo = JSONArray.toJSONString(vo);
            messagingTemplate.convertAndSend("/topic/pushRealAlarmInfo", alarmInfo);
        } catch (Exception e) {
            return new R<>(e);
        }
        return new R<>(true);
    }

    /**
     * 根据事件类型,查询报警事件
     *
     * @param eventType
     * @return
     */
    @GetMapping("/getAlarmInfoByEvent")
    public R getAlarmInfoByEvent(String eventType, String networkUnitId) {
        List<DmAlarminfoVo> list = null;
        List<String> unitIds = new ArrayList<>();
        try {
            if (StringUtils.isEmpty(networkUnitId)) {
                unitIds.addAll(sysUserService.getUserLoingUnitIds());
            } else {
                unitIds.add(networkUnitId);
            }
            list = dmAlarminfoService.getAlarmInfoByEvent(eventType, Constant.HANDLE_STATUS_NO, unitIds);
        } catch (Exception e) {
            return new R<>(e);
        }
        return new R<>(list);
    }

    /**
     * 通过id查询事件上送数据
     *
     * @param id id
     * @return R
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable("id") String id) {
        DmAlarminfo info = null;
        try {
            info = dmAlarminfoService.getById(id);
        } catch (Exception e) {
            return new R<>(e);
        }
        return new R<>(info);
    }

    /**
     * 新增事件上送数据
     *
     * @param dmAlarminfo 事件上送数据
     * @return R
     */
    @SysLog("新增事件上送数据")
    @PostMapping("/save")
    @Inner(value = false)
    public R save(@RequestBody DmAlarminfo dmAlarminfo) {
        try {
            dmAlarminfoService.saveDmAlarminfo(dmAlarminfo);
        } catch (Exception e) {
            return new R<>(e);
        }
        return new R<>(true);
    }

    /**
     * 修改事件上送数据
     *
     * @param dmAlarminfo 事件上送数据
     * @return R
     */
    @SysLog("修改事件上送数据")
    @PutMapping("/updateById")
    public R updateById(@RequestBody DmAlarminfo dmAlarminfo) {
        try {
            dmAlarminfoService.updateById(dmAlarminfo);
        } catch (Exception e) {
            return new R<>(e);
        }
        return new R<>(true);
    }

    /**
     * 通过id删除事件上送数据
     *
     * @param id id
     * @return R
     */
    @SysLog("删除事件上送数据")
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable String id) {
        return new R<>(dmAlarminfoService.removeById(id));
    }


    @GetMapping("/selectLastAlarm")
    public DmAlarminfo selectLastAlarm(@RequestParam("devCode") String devCode) {
        DmAlarminfo info = null;
        try {
            info = dmAlarminfoService.selectLastAlarm(devCode);
        } catch (Exception e) {
            e.getMessage();
        }
        return info;
    }

    /**
     * App告警周数据统计
     *
     * @param dmAlarminfo
     * @return
     */
    @GetMapping("/selectGroupDate")
    public R selectGroupDate(DmAlarminfo dmAlarminfo) {
        List<GroupDataVo> vos = null;
        try {
            List<String> list = sysUserService.getUserLoingUnitIds();
            dmAlarminfo.setUnitIds(list);
            vos = dmAlarminfoService.selectGraphStatistics(dmAlarminfo);
        } catch (Exception e) {
            return new R<>(e);
        }
        return new R<>(vos);
    }

    /**
     * 报警故障信息周统计
     *
     * @param dmAlarminfo
     * @return
     */
    @GetMapping("/findAlarmInfoWeek")
    public R findAlarmInfoWeek(DmAlarminfo dmAlarminfo) {
        List<DmAlarminfoVo> vos = null;
        try {
            List<String> list = sysUserService.getUserLoingUnitIds();
            dmAlarminfo.setUnitIds(list);
            dmAlarminfo.setDeviceStatus(Constant.HANDLE_STATUS_NO);
            vos = dmAlarminfoService.findAlarmInfoWeek(dmAlarminfo);
            if (vos != null) {
                for (DmAlarminfoVo vo : vos) {
                    LocalDateTime alarmTime = vo.getAlarmTime();
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd HH:mm:ss");
                    vo.setSimpleAlarmTime(df.format(alarmTime));
                }
            }
        } catch (Exception e) {
            return new R<>(e);
        }
        return new R<>(vos);
    }


    /**
     * 获取系统报告
     */
    @GetMapping("/getSystemReport")
    public R getSystemReport(Page page, String type) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        //周报
        if ("1".equals(type)) {
            String reportName = "北京东霖消防科技 第%s周 消防评估报告";
            for (int i = 1; i < 4; i++) {
                Map<String, String> m = new HashMap<String, String>();
                m.put("reportName", String.format(reportName, i));
                m.put("dateTime", df.format(new Date()));
                list.add(m);
            }
            //月报
        } else if ("2".equals(type)) {
            String reportName = "北京东霖消防科技 第%s月 消防评估报告";
            for (int i = 1; i < 13; i++) {
                Map<String, String> m = new HashMap<String, String>();
                m.put("reportName", String.format(reportName, i));
                m.put("dateTime", df.format(new Date()));
                list.add(m);
            }
            //年报
        } else if ("3".equals(type)) {
            String reportName = "北京东霖消防科技 %s年 消防评估报告";
            for (int i = 2019; i > 2016; i--) {
                Map<String, String> m = new HashMap<String, String>();
                m.put("reportName", String.format(reportName, i));
                m.put("dateTime", df.format(new Date()));
                list.add(m);
            }
        }
        return new R<>(page.setRecords(list));
    }

    /**
     * 查询实时报警数据
     */
    @GetMapping("/getRealAlarmData")
    public R getRealAlarmData(DmAlarminfo dmAlarminfo) {
        List<DmAlarminfoVo> vos = null;
        try {
            List<String> list = sysUserService.getUserLoingUnitIds();
            vos = dmAlarminfoService.findAlarmInfoByTime(dmAlarminfo, list);
            if (vos != null) {
                for (DmAlarminfoVo vo : vos) {
                    LocalDateTime alarmTime = vo.getAlarmTime();
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd HH:mm");
                    vo.setSimpleAlarmTime(df.format(alarmTime));
                }
            }
        } catch (Exception e) {
            return new R<>(e);
        }
        return new R<>(vos);
    }

    /**
     * 联网单位报警周统计
     *
     * @return
     */
    @GetMapping("/selectUnitAlarmDeviceStatistics")
    public R selectUnitAlarmDeviceStatistics() {
        List<Map> statistics = new ArrayList<>();
        try {
            List<String> list = sysUserService.getUserLoingUnitIds();
            //查询所属联网单位
            String name = "";
            List<BsNetworkingUnit> unitList = bsNetworkingUnitService.getBsNetworkingUnitList(list, name);
            List<Map> ms = dmAlarminfoService.selectUnitAlarmDeviceStatistics(list);
            for (BsNetworkingUnit info : unitList) {
                if (info == null || info.getUnitId() == null) continue;
                //初始化报警数据
                List<Map> ls = this.initAlarmCount();
                Map map = new HashMap();
                for (Map m : ms) {
                    if (m == null || m.get("typeId") == null || m.get("networkUnitId") == null) continue;
                    if (info.getUnitId().equals(m.get("networkUnitId"))) {
                        for (Map l : ls) {
                            if (l == null || l.get("typeId") == null) continue;
                            if (m.get("typeId").equals(l.get("typeId"))) {
                                l.put("counts", m.get("counts"));
                            }
                        }
                    }
                }
                map.put("networkUnitId", info.getUnitId());
                map.put("networkUnitName", info.getName());
                for (Map ma : ls) {
                    if (ma == null || ma.get("typeId") == null) continue;
                    if (Constant.PROD_TYPE_ELECTRIC_FIRE.equals(ma.get("typeId"))) {
                        map.put("electricFire", ma.get("counts"));
                    } else if (Constant.PROD_TYPE_REMOTE_VIDEO.equals(ma.get("typeId"))) {
                        map.put("video", ma.get("counts"));
                    } else if (Constant.PROD_TYPE_NB_SMOKE.equals(ma.get("typeId"))) {
                        map.put("nbSmoke", ma.get("counts"));
                    } else if (Constant.PROD_TYPE_FIRE_ALARM.equals(ma.get("typeId"))) {
                        map.put("fireAlarm", ma.get("counts"));
                    } else if (Constant.PROD_TYPE_WATER_SYS.equals(ma.get("typeId"))) {
                        map.put("water", ma.get("counts"));
                    } else {
                        map.put("other", ma.get("counts"));
                    }

                }
                statistics.add(map);
            }
        } catch (Exception e) {
            return new R<>(e);
        }
        return new R<>(statistics);
    }

    /**
     * 报警处理周统计
     *
     * @param dmAlarminfo
     * @return
     */
    @GetMapping("/tJAlarmInfo")
    public R tJAlarmInfo(DmAlarminfo dmAlarminfo) {
        List<String> list = null;
        List<Map> ls = new ArrayList<>();
        try {
            list = sysUserService.getUserLoingUnitIds();
            dmAlarminfo.setUnitIds(list);
            ls = dmAlarminfoService.tJAlarmInfo(dmAlarminfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new R<>(ls);
    }

    /**
     * 报警处理状态数量周统计
     *
     * @param dmAlarminfo
     * @return
     */
    @GetMapping("/findAlarmInfoCount")
    public R findAlarmInfoCountByTime(DmAlarminfo dmAlarminfo) {
        List<String> list = null;
        List<Map> ls = new ArrayList<>();
        try {
            list = sysUserService.getUserLoingUnitIds();
            dmAlarminfo.setUnitIds(list);
            ls = dmAlarminfoService.findAlarmInfoCountByTime(dmAlarminfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new R<>(ls);
    }

    /**
     * 消防设施报警趋势统计
     */
    @GetMapping("/selectDeviceAlarmTrendStatistics")
    public R selectDeviceAlarmTrendStatistics() {
        List<Map> ls = new ArrayList<>();
        try {
            List<String> list = sysUserService.getUserLoingUnitIds();
            ls = dmAlarminfoService.selectDeviceAlarmTrendStatistics(list);
        } catch (Exception e) {
            return new R<>(e);
        }
        return new R<>(ls);
    }

    /**
     * 初始报警数量
     *
     * @return
     */
    private List<Map> initAlarmCount() {
        List<Map> ls = new ArrayList<>();
        //初始化map,电器火灾
        Map fire = new HashMap();
        fire.put("typeId", Constant.PROD_TYPE_ELECTRIC_FIRE);
        fire.put("typeName", "电器火灾");
        fire.put("counts", 0);
        ls.add(fire);
        //初始化map,视频监控
        Map video = new HashMap();
        video.put("typeId", Constant.PROD_TYPE_REMOTE_VIDEO);
        video.put("typeName", "视频监控");
        video.put("counts", 0);
        ls.add(video);
        //初始化map,NB烟感
        Map smoke = new HashMap();
        smoke.put("typeId", Constant.PROD_TYPE_NB_SMOKE);
        smoke.put("typeName", "NB烟感");
        smoke.put("counts", 0);
        ls.add(smoke);
        //初始化map,火灾报警
        Map alarm = new HashMap();
        alarm.put("typeId", Constant.PROD_TYPE_FIRE_ALARM);
        alarm.put("typeName", "火灾报警");
        alarm.put("counts", 0);
        ls.add(alarm);
        //初始化map,水系统
        Map water = new HashMap();
        water.put("typeId", Constant.PROD_TYPE_WATER_SYS);
        water.put("typeName", "水系统");
        water.put("counts", 0);
        ls.add(water);
        //初始化map,其他系统
        Map other = new HashMap();
        other.put("typeId", Constant.PROD_TYPE_OTHER_SYS);
        other.put("typeName", "其他系统");
        other.put("counts", 0);
        ls.add(other);
        return ls;
    }
}
