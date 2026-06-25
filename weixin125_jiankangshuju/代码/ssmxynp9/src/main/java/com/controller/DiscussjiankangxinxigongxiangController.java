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

import com.entity.DiscussjiankangxinxigongxiangEntity;
import com.entity.view.DiscussjiankangxinxigongxiangView;

import com.service.DiscussjiankangxinxigongxiangService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;


/**
 * 健康信息共享评论
 * 后端接口
 */
@RestController
@RequestMapping("/discussjiankangxinxigongxiang")
public class DiscussjiankangxinxigongxiangController {
    @Autowired
    private DiscussjiankangxinxigongxiangService discussjiankangxinxigongxiangService;

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussjiankangxinxigongxiangEntity discussjiankangxinxigongxiang, HttpServletRequest request){

        EntityWrapper<DiscussjiankangxinxigongxiangEntity> ew = new EntityWrapper<DiscussjiankangxinxigongxiangEntity>();
        PageUtils page = discussjiankangxinxigongxiangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussjiankangxinxigongxiang), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussjiankangxinxigongxiangEntity discussjiankangxinxigongxiang, HttpServletRequest request){

        EntityWrapper<DiscussjiankangxinxigongxiangEntity> ew = new EntityWrapper<DiscussjiankangxinxigongxiangEntity>();
        PageUtils page = discussjiankangxinxigongxiangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussjiankangxinxigongxiang), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public R lists(DiscussjiankangxinxigongxiangEntity discussjiankangxinxigongxiang){
        EntityWrapper<DiscussjiankangxinxigongxiangEntity> ew = new EntityWrapper<DiscussjiankangxinxigongxiangEntity>();
        ew.allEq(MPUtil.allEQMapPre(discussjiankangxinxigongxiang, "discussjiankangxinxigongxiang"));
        return R.ok().put("data", discussjiankangxinxigongxiangService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussjiankangxinxigongxiangEntity discussjiankangxinxigongxiang){
        EntityWrapper<DiscussjiankangxinxigongxiangEntity> ew = new EntityWrapper<DiscussjiankangxinxigongxiangEntity>();
        ew.allEq(MPUtil.allEQMapPre(discussjiankangxinxigongxiang, "discussjiankangxinxigongxiang"));
        DiscussjiankangxinxigongxiangView discussjiankangxinxigongxiangView = discussjiankangxinxigongxiangService.selectView(ew);
        return R.ok("查询健康信息共享评论成功").put("data", discussjiankangxinxigongxiangView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussjiankangxinxigongxiangEntity discussjiankangxinxigongxiang = discussjiankangxinxigongxiangService.selectById(id);
        return R.ok().put("data", discussjiankangxinxigongxiang);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussjiankangxinxigongxiangEntity discussjiankangxinxigongxiang = discussjiankangxinxigongxiangService.selectById(id);
        return R.ok().put("data", discussjiankangxinxigongxiang);
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussjiankangxinxigongxiangEntity discussjiankangxinxigongxiang, HttpServletRequest request){
        discussjiankangxinxigongxiang.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        discussjiankangxinxigongxiang.setUserid((Long)request.getSession().getAttribute("userId"));
        discussjiankangxinxigongxiang.setNickname((String)request.getSession().getAttribute("username"));
        discussjiankangxinxigongxiangService.insert(discussjiankangxinxigongxiang);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussjiankangxinxigongxiangEntity discussjiankangxinxigongxiang, HttpServletRequest request){
        discussjiankangxinxigongxiang.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        discussjiankangxinxigongxiang.setUserid((Long)request.getSession().getAttribute("userId"));
        discussjiankangxinxigongxiang.setNickname((String)request.getSession().getAttribute("username"));
        discussjiankangxinxigongxiangService.insert(discussjiankangxinxigongxiang);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DiscussjiankangxinxigongxiangEntity discussjiankangxinxigongxiang, HttpServletRequest request){
        discussjiankangxinxigongxiangService.updateById(discussjiankangxinxigongxiang);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussjiankangxinxigongxiangService.deleteBatchIds(Arrays.asList(ids));
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

        Wrapper<DiscussjiankangxinxigongxiangEntity> wrapper = new EntityWrapper<DiscussjiankangxinxigongxiangEntity>();
        if(map.get("remindstart")!=null) {
            wrapper.ge(columnName, map.get("remindstart"));
        }
        if(map.get("remindend")!=null) {
            wrapper.le(columnName, map.get("remindend"));
        }

        int count = discussjiankangxinxigongxiangService.selectCount(wrapper);
        return R.ok().put("count", count);
    }

}
