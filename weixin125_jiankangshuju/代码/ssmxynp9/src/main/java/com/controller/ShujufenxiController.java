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

import com.entity.ShujufenxiEntity;
import com.entity.view.ShujufenxiView;

import com.service.ShujufenxiService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;


/**
 * 数据分析
 * 后端接口
 */
@RestController
@RequestMapping("/shujufenxi")
public class ShujufenxiController {
    @Autowired
    private ShujufenxiService shujufenxiService;

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShujufenxiEntity shujufenxi, HttpServletRequest request){
        if(request.getSession().getAttribute("role") != null && !request.getSession().getAttribute("role").toString().equals("管理员")) {
            shujufenxi.setUserid((Long)request.getSession().getAttribute("userId"));
        }

        EntityWrapper<ShujufenxiEntity> ew = new EntityWrapper<ShujufenxiEntity>();
        PageUtils page = shujufenxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shujufenxi), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ShujufenxiEntity shujufenxi, HttpServletRequest request){
        if(request.getSession().getAttribute("role") != null && !request.getSession().getAttribute("role").toString().equals("管理员")) {
            shujufenxi.setUserid((Long)request.getSession().getAttribute("userId"));
        }

        EntityWrapper<ShujufenxiEntity> ew = new EntityWrapper<ShujufenxiEntity>();
        PageUtils page = shujufenxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shujufenxi), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public R lists(ShujufenxiEntity shujufenxi){
        EntityWrapper<ShujufenxiEntity> ew = new EntityWrapper<ShujufenxiEntity>();
        ew.allEq(MPUtil.allEQMapPre(shujufenxi, "shujufenxi"));
        return R.ok().put("data", shujufenxiService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ShujufenxiEntity shujufenxi){
        EntityWrapper<ShujufenxiEntity> ew = new EntityWrapper<ShujufenxiEntity>();
        ew.allEq(MPUtil.allEQMapPre(shujufenxi, "shujufenxi"));
        ShujufenxiView shujufenxiView = shujufenxiService.selectView(ew);
        return R.ok("查询数据分析成功").put("data", shujufenxiView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ShujufenxiEntity shujufenxi = shujufenxiService.selectById(id);
        return R.ok().put("data", shujufenxi);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ShujufenxiEntity shujufenxi = shujufenxiService.selectById(id);
        return R.ok().put("data", shujufenxi);
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ShujufenxiEntity shujufenxi, HttpServletRequest request){
        shujufenxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        shujufenxi.setUserid((Long)request.getSession().getAttribute("userId"));
        shujufenxiService.insert(shujufenxi);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ShujufenxiEntity shujufenxi, HttpServletRequest request){
        shujufenxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        shujufenxi.setUserid((Long)request.getSession().getAttribute("userId"));
        shujufenxiService.insert(shujufenxi);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ShujufenxiEntity shujufenxi, HttpServletRequest request){
        shujufenxiService.updateById(shujufenxi);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        shujufenxiService.deleteBatchIds(Arrays.asList(ids));
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

        Wrapper<ShujufenxiEntity> wrapper = new EntityWrapper<ShujufenxiEntity>();
        if(map.get("remindstart")!=null) {
            wrapper.ge(columnName, map.get("remindstart"));
        }
        if(map.get("remindend")!=null) {
            wrapper.le(columnName, map.get("remindend"));
        }

        int count = shujufenxiService.selectCount(wrapper);
        return R.ok().put("count", count);
    }

}
