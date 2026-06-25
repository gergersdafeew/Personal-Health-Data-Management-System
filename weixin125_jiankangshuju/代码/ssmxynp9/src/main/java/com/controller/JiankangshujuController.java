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

import com.entity.JiankangshujuEntity;
import com.entity.view.JiankangshujuView;

import com.service.JiankangshujuService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;


/**
 * 健康数据
 * 后端接口
 */
@RestController
@RequestMapping("/jiankangshuju")
public class JiankangshujuController {
    @Autowired
    private JiankangshujuService jiankangshujuService;

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiankangshujuEntity jiankangshuju, HttpServletRequest request){
        if(request.getSession().getAttribute("role") != null && !request.getSession().getAttribute("role").toString().equals("管理员")) {
            jiankangshuju.setUserid((Long)request.getSession().getAttribute("userId"));
        }

        EntityWrapper<JiankangshujuEntity> ew = new EntityWrapper<JiankangshujuEntity>();
        PageUtils page = jiankangshujuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiankangshuju), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiankangshujuEntity jiankangshuju, HttpServletRequest request){
        if(request.getSession().getAttribute("role") != null && !request.getSession().getAttribute("role").toString().equals("管理员")) {
            jiankangshuju.setUserid((Long)request.getSession().getAttribute("userId"));
        }

        EntityWrapper<JiankangshujuEntity> ew = new EntityWrapper<JiankangshujuEntity>();
        PageUtils page = jiankangshujuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiankangshuju), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public R lists(JiankangshujuEntity jiankangshuju){
        EntityWrapper<JiankangshujuEntity> ew = new EntityWrapper<JiankangshujuEntity>();
        ew.allEq(MPUtil.allEQMapPre(jiankangshuju, "jiankangshuju"));
        return R.ok().put("data", jiankangshujuService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiankangshujuEntity jiankangshuju){
        EntityWrapper<JiankangshujuEntity> ew = new EntityWrapper<JiankangshujuEntity>();
        ew.allEq(MPUtil.allEQMapPre(jiankangshuju, "jiankangshuju"));
        JiankangshujuView jiankangshujuView = jiankangshujuService.selectView(ew);
        return R.ok("查询健康数据成功").put("data", jiankangshujuView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiankangshujuEntity jiankangshuju = jiankangshujuService.selectById(id);
        return R.ok().put("data", jiankangshuju);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiankangshujuEntity jiankangshuju = jiankangshujuService.selectById(id);
        return R.ok().put("data", jiankangshuju);
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiankangshujuEntity jiankangshuju, HttpServletRequest request){
        jiankangshuju.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        jiankangshuju.setUserid((Long)request.getSession().getAttribute("userId"));
        jiankangshujuService.insert(jiankangshuju);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiankangshujuEntity jiankangshuju, HttpServletRequest request){
        jiankangshuju.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        jiankangshuju.setUserid((Long)request.getSession().getAttribute("userId"));
        jiankangshujuService.insert(jiankangshuju);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody JiankangshujuEntity jiankangshuju, HttpServletRequest request){
        jiankangshujuService.updateById(jiankangshuju);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiankangshujuService.deleteBatchIds(Arrays.asList(ids));
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

        Wrapper<JiankangshujuEntity> wrapper = new EntityWrapper<JiankangshujuEntity>();
        if(map.get("remindstart")!=null) {
            wrapper.ge(columnName, map.get("remindstart"));
        }
        if(map.get("remindend")!=null) {
            wrapper.le(columnName, map.get("remindend"));
        }

        int count = jiankangshujuService.selectCount(wrapper);
        return R.ok().put("count", count);
    }

}
