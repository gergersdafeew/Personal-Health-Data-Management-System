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

import com.entity.JiankangxinxigongxiangEntity;
import com.entity.view.JiankangxinxigongxiangView;

import com.service.JiankangxinxigongxiangService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;


/**
 * 健康信息共享
 * 后端接口
 */
@RestController
@RequestMapping("/jiankangxinxigongxiang")
public class JiankangxinxigongxiangController {
    @Autowired
    private JiankangxinxigongxiangService jiankangxinxigongxiangService;

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiankangxinxigongxiangEntity jiankangxinxigongxiang, HttpServletRequest request){

        EntityWrapper<JiankangxinxigongxiangEntity> ew = new EntityWrapper<JiankangxinxigongxiangEntity>();
        PageUtils page = jiankangxinxigongxiangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiankangxinxigongxiang), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiankangxinxigongxiangEntity jiankangxinxigongxiang, HttpServletRequest request){

        EntityWrapper<JiankangxinxigongxiangEntity> ew = new EntityWrapper<JiankangxinxigongxiangEntity>();
        PageUtils page = jiankangxinxigongxiangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiankangxinxigongxiang), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public R lists(JiankangxinxigongxiangEntity jiankangxinxigongxiang){
        EntityWrapper<JiankangxinxigongxiangEntity> ew = new EntityWrapper<JiankangxinxigongxiangEntity>();
        ew.allEq(MPUtil.allEQMapPre(jiankangxinxigongxiang, "jiankangxinxigongxiang"));
        return R.ok().put("data", jiankangxinxigongxiangService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiankangxinxigongxiangEntity jiankangxinxigongxiang){
        EntityWrapper<JiankangxinxigongxiangEntity> ew = new EntityWrapper<JiankangxinxigongxiangEntity>();
        ew.allEq(MPUtil.allEQMapPre(jiankangxinxigongxiang, "jiankangxinxigongxiang"));
        JiankangxinxigongxiangView jiankangxinxigongxiangView = jiankangxinxigongxiangService.selectView(ew);
        return R.ok("查询健康信息共享成功").put("data", jiankangxinxigongxiangView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiankangxinxigongxiangEntity jiankangxinxigongxiang = jiankangxinxigongxiangService.selectById(id);
        return R.ok().put("data", jiankangxinxigongxiang);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiankangxinxigongxiangEntity jiankangxinxigongxiang = jiankangxinxigongxiangService.selectById(id);
        return R.ok().put("data", jiankangxinxigongxiang);
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiankangxinxigongxiangEntity jiankangxinxigongxiang, HttpServletRequest request){
        jiankangxinxigongxiang.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        jiankangxinxigongxiangService.insert(jiankangxinxigongxiang);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiankangxinxigongxiangEntity jiankangxinxigongxiang, HttpServletRequest request){
        jiankangxinxigongxiang.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        jiankangxinxigongxiangService.insert(jiankangxinxigongxiang);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody JiankangxinxigongxiangEntity jiankangxinxigongxiang, HttpServletRequest request){
        jiankangxinxigongxiangService.updateById(jiankangxinxigongxiang);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiankangxinxigongxiangService.deleteBatchIds(Arrays.asList(ids));
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

        Wrapper<JiankangxinxigongxiangEntity> wrapper = new EntityWrapper<JiankangxinxigongxiangEntity>();
        if(map.get("remindstart")!=null) {
            wrapper.ge(columnName, map.get("remindstart"));
        }
        if(map.get("remindend")!=null) {
            wrapper.le(columnName, map.get("remindend"));
        }

        int count = jiankangxinxigongxiangService.selectCount(wrapper);
        return R.ok().put("count", count);
    }

    /**
     * 前端智能排序
     */
    @IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,JiankangxinxigongxiangEntity jiankangxinxigongxiang, HttpServletRequest request){
        EntityWrapper<JiankangxinxigongxiangEntity> ew = new EntityWrapper<JiankangxinxigongxiangEntity>();
        params.put("sort", "clicknum");
        params.put("order", "desc");
        PageUtils page = jiankangxinxigongxiangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiankangxinxigongxiang), params), params));
        return R.ok().put("data", page);
    }

}
