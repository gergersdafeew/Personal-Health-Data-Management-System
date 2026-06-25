package com.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.JiankangjihuaEntity;
import com.entity.view.JiankangjihuaView;

import com.service.JiankangjihuaService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;


/**
 * 健康计划
 * 后端接口
 */
@RestController
@RequestMapping("/jiankangjihua")
public class JiankangjihuaController {
    @Autowired
    private JiankangjihuaService jiankangjihuaService;

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiankangjihuaEntity jiankangjihua, HttpServletRequest request){

        EntityWrapper<JiankangjihuaEntity> ew = new EntityWrapper<JiankangjihuaEntity>();
        PageUtils page = jiankangjihuaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiankangjihua), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiankangjihuaEntity jiankangjihua, HttpServletRequest request){

        EntityWrapper<JiankangjihuaEntity> ew = new EntityWrapper<JiankangjihuaEntity>();
        PageUtils page = jiankangjihuaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiankangjihua), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public R lists(JiankangjihuaEntity jiankangjihua){
        EntityWrapper<JiankangjihuaEntity> ew = new EntityWrapper<JiankangjihuaEntity>();
        ew.allEq(MPUtil.allEQMapPre(jiankangjihua, "jiankangjihua"));
        return R.ok().put("data", jiankangjihuaService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiankangjihuaEntity jiankangjihua){
        EntityWrapper<JiankangjihuaEntity> ew = new EntityWrapper<JiankangjihuaEntity>();
        ew.allEq(MPUtil.allEQMapPre(jiankangjihua, "jiankangjihua"));
        JiankangjihuaView jiankangjihuaView = jiankangjihuaService.selectView(ew);
        return R.ok("查询健康计划成功").put("data", jiankangjihuaView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiankangjihuaEntity jiankangjihua = jiankangjihuaService.selectById(id);
        return R.ok().put("data", jiankangjihua);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiankangjihuaEntity jiankangjihua = jiankangjihuaService.selectById(id);
        return R.ok().put("data", jiankangjihua);
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiankangjihuaEntity jiankangjihua, HttpServletRequest request){
        jiankangjihua.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        jiankangjihuaService.insert(jiankangjihua);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiankangjihuaEntity jiankangjihua, HttpServletRequest request){
        jiankangjihua.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        jiankangjihuaService.insert(jiankangjihua);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody JiankangjihuaEntity jiankangjihua, HttpServletRequest request){
        jiankangjihuaService.updateById(jiankangjihua);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiankangjihuaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 提醒接口
     */
    @RequestMapping("/remind/{columnName}/{type}")
    public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request,
                         @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
        map.put("column", columnName);
        map.put("type", type);

        if(type.equals("2")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Date remindStartDate = null;
            Date remindEndDate = null;
            if(map.get("remindstart")!=null) {
                Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH,remindStart);
                remindStartDate = c.getTime();
                map.put("remindstart", sdf.format(remindStartDate));
            }
            if(map.get("remindend")!=null) {
                Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH,remindEnd);
                remindEndDate = c.getTime();
                map.put("remindend", sdf.format(remindEndDate));
            }
        }

        Wrapper<JiankangjihuaEntity> wrapper = new EntityWrapper<JiankangjihuaEntity>();
        if(map.get("remindstart")!=null) {
            wrapper.ge(columnName, map.get("remindstart"));
        }
        if(map.get("remindend")!=null) {
            wrapper.le(columnName, map.get("remindend"));
        }

        int count = jiankangjihuaService.selectCount(wrapper);
        return R.ok().put("count", count);
    }

    /**
     * 前端智能排序
     */
    @IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,JiankangjihuaEntity jiankangjihua, HttpServletRequest request){
        EntityWrapper<JiankangjihuaEntity> ew = new EntityWrapper<JiankangjihuaEntity>();
        params.put("sort", "clicknum");
        params.put("order", "desc");
        PageUtils page = jiankangjihuaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiankangjihua), params), params));
        return R.ok().put("data", page);
    }

}
