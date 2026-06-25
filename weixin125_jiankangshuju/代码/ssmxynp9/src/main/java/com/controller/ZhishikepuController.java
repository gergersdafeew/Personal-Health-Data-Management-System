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

import com.entity.ZhishikepuEntity;
import com.entity.view.ZhishikepuView;

import com.service.ZhishikepuService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;


/**
 * 知识科普
 * 后端接口
 */
@RestController
@RequestMapping("/zhishikepu")
public class ZhishikepuController {
    @Autowired
    private ZhishikepuService zhishikepuService;

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZhishikepuEntity zhishikepu, HttpServletRequest request){

        EntityWrapper<ZhishikepuEntity> ew = new EntityWrapper<ZhishikepuEntity>();
        PageUtils page = zhishikepuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhishikepu), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZhishikepuEntity zhishikepu, HttpServletRequest request){

        EntityWrapper<ZhishikepuEntity> ew = new EntityWrapper<ZhishikepuEntity>();
        PageUtils page = zhishikepuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhishikepu), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public R lists(ZhishikepuEntity zhishikepu){
        EntityWrapper<ZhishikepuEntity> ew = new EntityWrapper<ZhishikepuEntity>();
        ew.allEq(MPUtil.allEQMapPre(zhishikepu, "zhishikepu"));
        return R.ok().put("data", zhishikepuService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhishikepuEntity zhishikepu){
        EntityWrapper<ZhishikepuEntity> ew = new EntityWrapper<ZhishikepuEntity>();
        ew.allEq(MPUtil.allEQMapPre(zhishikepu, "zhishikepu"));
        ZhishikepuView zhishikepuView = zhishikepuService.selectView(ew);
        return R.ok("查询知识科普成功").put("data", zhishikepuView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhishikepuEntity zhishikepu = zhishikepuService.selectById(id);
        return R.ok().put("data", zhishikepu);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZhishikepuEntity zhishikepu = zhishikepuService.selectById(id);
        return R.ok().put("data", zhishikepu);
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZhishikepuEntity zhishikepu, HttpServletRequest request){
        zhishikepu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        zhishikepuService.insert(zhishikepu);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZhishikepuEntity zhishikepu, HttpServletRequest request){
        zhishikepu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        zhishikepuService.insert(zhishikepu);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ZhishikepuEntity zhishikepu, HttpServletRequest request){
        zhishikepuService.updateById(zhishikepu);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zhishikepuService.deleteBatchIds(Arrays.asList(ids));
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

        Wrapper<ZhishikepuEntity> wrapper = new EntityWrapper<ZhishikepuEntity>();
        if(map.get("remindstart")!=null) {
            wrapper.ge(columnName, map.get("remindstart"));
        }
        if(map.get("remindend")!=null) {
            wrapper.le(columnName, map.get("remindend"));
        }

        int count = zhishikepuService.selectCount(wrapper);
        return R.ok().put("count", count);
    }

    /**
     * 前端智能排序
     */
    @IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,ZhishikepuEntity zhishikepu, HttpServletRequest request){
        EntityWrapper<ZhishikepuEntity> ew = new EntityWrapper<ZhishikepuEntity>();
        params.put("sort", "clicknum");
        params.put("order", "desc");
        PageUtils page = zhishikepuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhishikepu), params), params));
        return R.ok().put("data", page);
    }

}
