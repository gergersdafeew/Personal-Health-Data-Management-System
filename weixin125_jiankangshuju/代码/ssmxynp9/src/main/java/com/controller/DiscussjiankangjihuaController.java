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

import com.entity.DiscussjiankangjihuaEntity;
import com.entity.view.DiscussjiankangjihuaView;

import com.service.DiscussjiankangjihuaService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;


/**
 * 健康计划评论
 * 后端接口
 */
@RestController
@RequestMapping("/discussjiankangjihua")
public class DiscussjiankangjihuaController {
    @Autowired
    private DiscussjiankangjihuaService discussjiankangjihuaService;

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussjiankangjihuaEntity discussjiankangjihua, HttpServletRequest request){

        EntityWrapper<DiscussjiankangjihuaEntity> ew = new EntityWrapper<DiscussjiankangjihuaEntity>();
        PageUtils page = discussjiankangjihuaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussjiankangjihua), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussjiankangjihuaEntity discussjiankangjihua, HttpServletRequest request){

        EntityWrapper<DiscussjiankangjihuaEntity> ew = new EntityWrapper<DiscussjiankangjihuaEntity>();
        PageUtils page = discussjiankangjihuaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussjiankangjihua), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public R lists(DiscussjiankangjihuaEntity discussjiankangjihua){
        EntityWrapper<DiscussjiankangjihuaEntity> ew = new EntityWrapper<DiscussjiankangjihuaEntity>();
        ew.allEq(MPUtil.allEQMapPre(discussjiankangjihua, "discussjiankangjihua"));
        return R.ok().put("data", discussjiankangjihuaService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussjiankangjihuaEntity discussjiankangjihua){
        EntityWrapper<DiscussjiankangjihuaEntity> ew = new EntityWrapper<DiscussjiankangjihuaEntity>();
        ew.allEq(MPUtil.allEQMapPre(discussjiankangjihua, "discussjiankangjihua"));
        DiscussjiankangjihuaView discussjiankangjihuaView = discussjiankangjihuaService.selectView(ew);
        return R.ok("查询健康计划评论成功").put("data", discussjiankangjihuaView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussjiankangjihuaEntity discussjiankangjihua = discussjiankangjihuaService.selectById(id);
        return R.ok().put("data", discussjiankangjihua);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussjiankangjihuaEntity discussjiankangjihua = discussjiankangjihuaService.selectById(id);
        return R.ok().put("data", discussjiankangjihua);
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussjiankangjihuaEntity discussjiankangjihua, HttpServletRequest request){
        discussjiankangjihua.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        discussjiankangjihua.setUserid((Long)request.getSession().getAttribute("userId"));
        discussjiankangjihua.setNickname((String)request.getSession().getAttribute("username"));
        discussjiankangjihuaService.insert(discussjiankangjihua);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussjiankangjihuaEntity discussjiankangjihua, HttpServletRequest request){
        discussjiankangjihua.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        discussjiankangjihua.setUserid((Long)request.getSession().getAttribute("userId"));
        discussjiankangjihua.setNickname((String)request.getSession().getAttribute("username"));
        discussjiankangjihuaService.insert(discussjiankangjihua);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DiscussjiankangjihuaEntity discussjiankangjihua, HttpServletRequest request){
        discussjiankangjihuaService.updateById(discussjiankangjihua);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussjiankangjihuaService.deleteBatchIds(Arrays.asList(ids));
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

        Wrapper<DiscussjiankangjihuaEntity> wrapper = new EntityWrapper<DiscussjiankangjihuaEntity>();
        if(map.get("remindstart")!=null) {
            wrapper.ge(columnName, map.get("remindstart"));
        }
        if(map.get("remindend")!=null) {
            wrapper.le(columnName, map.get("remindend"));
        }

        int count = discussjiankangjihuaService.selectCount(wrapper);
        return R.ok().put("count", count);
    }

}
