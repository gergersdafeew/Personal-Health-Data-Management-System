<template>
  <div class="home-dashboard">
    <div class="welcome-bar">
      <div>
        <div class="welcome-title">欢迎使用 {{ $project.projectName }}</div>
        <div class="welcome-sub">
          {{ adminName ? adminName + '，' : '' }}个人健康数据管理后台 · 一站式数据监控与运营
        </div>
      </div>
      <div class="welcome-right">
        <div class="welcome-date">{{ today }}</div>
        <div class="welcome-badge">平台总记录 {{ platformTotal }}</div>
      </div>
    </div>

    <el-row :gutter="16" class="stat-row">
      <el-col :xs="12" :sm="8" :md="4" v-for="item in statCards" :key="item.key">
        <div class="stat-card" :style="{ borderTopColor: item.color }" @click="goPage(item.path)">
          <div class="stat-label">{{ item.label }}</div>
          <div class="stat-value">{{ stats[item.key] }}</div>
          <div class="stat-unit">{{ item.unit }}</div>
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="16" class="stat-row">
      <el-col :xs="12" :sm="6" v-for="item in extraCards" :key="item.key">
        <div class="extra-card" @click="item.path && goPage(item.path)">
          <i :class="item.icon" :style="{ color: item.color }"></i>
          <div class="extra-info">
            <div class="extra-value">{{ extraStats[item.key] }}</div>
            <div class="extra-label">{{ item.label }}</div>
          </div>
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="16" class="panel-row">
      <el-col :xs="24" :md="14">
        <el-card shadow="hover" class="panel-card">
          <div slot="header" class="panel-header">数据生态关系图谱</div>
          <div class="chart-tip">可拖拽节点、滚轮缩放，点击图例筛选模块</div>
          <div id="home-chart" class="home-chart"></div>
        </el-card>
      </el-col>
      <el-col :xs="24" :md="10">
        <el-card shadow="hover" class="panel-card">
          <div slot="header" class="panel-header">快捷管理</div>
          <div class="quick-links">
            <el-button
              v-for="link in quickLinks"
              :key="link.path"
              type="primary"
              plain
              size="small"
              @click="goPage(link.path)"
            >{{ link.label }}</el-button>
          </div>
        </el-card>
        <el-card shadow="hover" class="panel-card" style="margin-top:16px;">
          <div slot="header" class="panel-header">热门科普 TOP5</div>
          <el-table :data="hotList" size="small" stripe style="width:100%">
            <el-table-column type="index" width="40" />
            <el-table-column prop="biaoti" label="标题" show-overflow-tooltip />
            <el-table-column prop="clicknum" label="点击" width="60" align="center" />
            <el-table-column prop="thumbsupnum" label="赞" width="50" align="center" />
          </el-table>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="16" class="panel-row">
      <el-col :xs="24" :md="12">
        <el-card shadow="hover" class="panel-card">
          <div slot="header" class="panel-header">数据层次旭日图</div>
          <div id="home-sunburst" class="sub-chart"></div>
        </el-card>
      </el-col>
      <el-col :xs="24" :md="12">
        <el-card shadow="hover" class="panel-card">
          <div slot="header" class="panel-header">评论互动分布</div>
          <div id="home-comment-chart" class="sub-chart"></div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="16" class="panel-row">
      <el-col :xs="24" :md="12">
        <el-card shadow="hover" class="panel-card">
          <div slot="header" class="panel-header">健康分析结果分布</div>
          <div id="home-analysis-chart" class="sub-chart"></div>
        </el-card>
      </el-col>
      <el-col :xs="24" :md="12">
        <el-card shadow="hover" class="panel-card">
          <div slot="header" class="panel-header">科普互动概览</div>
          <div id="home-engage-chart" class="sub-chart"></div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="16" class="panel-row">
      <el-col :span="24">
        <el-card shadow="hover" class="panel-card">
          <div slot="header" class="panel-header">最新动态</div>
          <el-tabs v-model="activeTab">
            <el-tab-pane label="最新用户" name="user">
              <el-table :data="recentUsers" size="small" stripe>
                <el-table-column prop="yonghuzhanghao" label="账号" />
                <el-table-column prop="yonghuming" label="用户名" />
                <el-table-column prop="xingbie" label="性别" width="60" />
                <el-table-column prop="lianxidianhua" label="电话" />
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="最新健康数据" name="health">
              <el-table :data="recentHealth" size="small" stripe>
                <el-table-column prop="yonghuming" label="用户" width="90" />
                <el-table-column prop="nianling" label="年龄" width="60" />
                <el-table-column prop="shengao" label="身高" width="80" />
                <el-table-column prop="tizhong" label="体重" width="80" />
                <el-table-column prop="fabushijian" label="发布时间" show-overflow-tooltip />
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="最新评论" name="comment">
              <el-table :data="recentComments" size="small" stripe>
                <el-table-column prop="module" label="来源" width="100" />
                <el-table-column prop="nickname" label="用户" width="90" />
                <el-table-column prop="content" label="评论内容" show-overflow-tooltip />
                <el-table-column prop="addtime" label="时间" width="160" />
              </el-table>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="16" class="panel-row">
      <el-col :xs="24" :md="8" v-for="tip in platformTips" :key="tip.title">
        <div class="tip-card">
          <div class="tip-title"><i :class="tip.icon"></i> {{ tip.title }}</div>
          <div class="tip-desc">{{ tip.desc }}</div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import router from '@/router/router-static'

export default {
  data() {
    return {
      today: '',
      adminName: '',
      activeTab: 'user',
      stats: {
        yonghu: 0,
        zhishikepu: 0,
        jiankangjihua: 0,
        jiankangxinxigongxiang: 0,
        jiankangshuju: 0,
        shujufenxi: 0
      },
      extraStats: {
        discussTotal: 0,
        totalClick: 0,
        totalThumb: 0,
        zhishikepuComment: 0
      },
      commentModuleStats: {
        discusszhishikepu: 0,
        discussjiankangjihua: 0,
        discussjiankangxinxigongxiang: 0
      },
      statCards: [
        { key: 'yonghu', label: '注册用户', unit: '人', color: '#409EFF', path: '/yonghu' },
        { key: 'zhishikepu', label: '知识科普', unit: '篇', color: '#67C23A', path: '/zhishikepu' },
        { key: 'jiankangjihua', label: '健康计划', unit: '条', color: '#E6A23C', path: '/jiankangjihua' },
        { key: 'jiankangxinxigongxiang', label: '健康共享', unit: '条', color: '#F56C6C', path: '/jiankangxinxigongxiang' },
        { key: 'jiankangshuju', label: '健康数据', unit: '条', color: '#909399', path: '/jiankangshuju' },
        { key: 'shujufenxi', label: '数据分析', unit: '条', color: '#26C6DA', path: '/shujufenxi' }
      ],
      extraCards: [
        { key: 'discussTotal', label: '评论总数', icon: 'el-icon-chat-dot-round', color: '#409EFF', path: '/discusszhishikepu' },
        { key: 'totalClick', label: '科普总点击', icon: 'el-icon-view', color: '#67C23A', path: '/zhishikepu' },
        { key: 'totalThumb', label: '科普总点赞', icon: 'el-icon-star-off', color: '#E6A23C', path: '/zhishikepu' },
        { key: 'zhishikepuComment', label: '科普评论', icon: 'el-icon-message', color: '#F56C6C', path: '/discusszhishikepu' }
      ],
      quickLinks: [
        { label: '用户管理', path: '/yonghu' },
        { label: '知识科普', path: '/zhishikepu' },
        { label: '健康计划', path: '/jiankangjihua' },
        { label: '健康共享', path: '/jiankangxinxigongxiang' },
        { label: '健康数据', path: '/jiankangshuju' },
        { label: '数据分析', path: '/shujufenxi' },
        { label: '轮播图管理', path: '/config' },
        { label: '科普评论', path: '/discusszhishikepu' },
        { label: '计划评论', path: '/discussjiankangjihua' },
        { label: '共享评论', path: '/discussjiankangxinxigongxiang' }
      ],
      platformTips: [
        { title: '数据管理', desc: '健康数据模块记录用户体征与习惯，支持一键跳转数据分析。', icon: 'el-icon-data-line' },
        { title: '内容运营', desc: '知识科普支持视频与图文，可通过评论模块查看用户反馈。', icon: 'el-icon-video-camera' },
        { title: '用户服务', desc: '健康计划与健康共享帮助用户制定目标、交流经验。', icon: 'el-icon-user' }
      ],
      hotList: [],
      recentUsers: [],
      recentHealth: [],
      recentComments: [],
      analysisDist: {},
      engageData: { clicks: [], thumbs: [] },
      charts: {}
    }
  },
  computed: {
    platformTotal() {
      return Object.keys(this.stats).reduce((sum, k) => sum + (this.stats[k] || 0), 0)
    }
  },
  mounted() {
    this.today = this.formatDate(new Date())
    this.init()
  },
  beforeDestroy() {
    Object.keys(this.charts).forEach(k => {
      if (this.charts[k]) {
        this.charts[k].dispose()
      }
    })
    this.charts = {}
    window.onresize = null
  },
  methods: {
    init() {
      if (this.$storage.get('Token')) {
        this.$http({
          url: `${this.$storage.get('sessionTable')}/session`,
          method: 'get'
        }).then(({ data }) => {
          if (data && data.code != 0) {
            router.push({ name: 'login' })
          } else {
            if (data.data) {
              this.adminName = data.data.username || data.data.yonghuming || '管理员'
            }
            this.loadDashboard()
          }
        })
      } else {
        router.push({ name: 'login' })
      }
    },
    formatDate(date) {
      const y = date.getFullYear()
      const m = String(date.getMonth() + 1).padStart(2, '0')
      const d = String(date.getDate()).padStart(2, '0')
      const week = ['日', '一', '二', '三', '四', '五', '六'][date.getDay()]
      return `${y}-${m}-${d} 星期${week}`
    },
    fetchTotal(module) {
      return this.$http({
        url: `${module}/page`,
        method: 'get',
        params: { page: 1, limit: 1 }
      }).then(({ data }) => (data && data.code === 0 ? data.data.total : 0))
    },
    fetchList(module, limit, sort, order) {
      const params = { page: 1, limit: limit || 10 }
      if (sort) {
        params.sort = sort
        params.order = order || 'desc'
      }
      return this.$http({
        url: `${module}/page`,
        method: 'get',
        params
      }).then(({ data }) => (data && data.code === 0 ? data.data.list || [] : []))
    },
    loadDashboard() {
      const modules = Object.keys(this.stats)
      Promise.all(modules.map(m => this.fetchTotal(m))).then(values => {
        modules.forEach((m, i) => { this.stats[m] = values[i] })
        this.$nextTick(() => {
          this.renderGraphChart()
          this.renderSunburstChart()
        })
      })

      const discussModules = [
        { key: 'discusszhishikepu', label: '科普评论' },
        { key: 'discussjiankangjihua', label: '计划评论' },
        { key: 'discussjiankangxinxigongxiang', label: '共享评论' }
      ]
      Promise.all(discussModules.map(m => this.fetchTotal(m.key))).then(values => {
        let total = 0
        discussModules.forEach((m, i) => {
          this.commentModuleStats[m.key] = values[i]
          total += values[i]
        })
        this.extraStats.discussTotal = total
        this.extraStats.zhishikepuComment = values[0]
        this.$nextTick(() => this.renderCommentChart(discussModules))
      })

      this.fetchList('zhishikepu', 5, 'clicknum', 'desc').then(list => {
        this.hotList = list
      })

      this.fetchList('zhishikepu', 50).then(list => {
        let clicks = 0
        let thumbs = 0
        const names = []
        const clickArr = []
        const thumbArr = []
        list.forEach(item => {
          clicks += Number(item.clicknum) || 0
          thumbs += Number(item.thumbsupnum) || 0
          names.push(item.biaoti ? item.biaoti.substring(0, 8) : '科普')
          clickArr.push(Number(item.clicknum) || 0)
          thumbArr.push(Number(item.thumbsupnum) || 0)
        })
        this.extraStats.totalClick = clicks
        this.extraStats.totalThumb = thumbs
        this.engageData = { names: names.slice(0, 6), clicks: clickArr.slice(0, 6), thumbs: thumbArr.slice(0, 6) }
        this.$nextTick(() => this.renderEngageChart())
      })

      this.fetchList('yonghu', 5, 'id', 'desc').then(list => { this.recentUsers = list })
      this.fetchList('jiankangshuju', 5, 'id', 'desc').then(list => { this.recentHealth = list })

      Promise.all([
        this.fetchList('discusszhishikepu', 3, 'id', 'desc'),
        this.fetchList('discussjiankangjihua', 3, 'id', 'desc'),
        this.fetchList('discussjiankangxinxigongxiang', 3, 'id', 'desc')
      ]).then(results => {
        const labels = ['科普', '计划', '共享']
        const merged = []
        results.forEach((list, i) => {
          list.forEach(c => {
            merged.push({
              module: labels[i],
              nickname: c.nickname,
              content: c.content,
              addtime: c.addtime
            })
          })
        })
        this.recentComments = merged.slice(0, 8)
      })

      this.fetchList('shujufenxi', 50).then(list => {
        const dist = {}
        list.forEach(item => {
          const key = item.fenxijieguo || '未知'
          dist[key] = (dist[key] || 0) + 1
        })
        this.analysisDist = dist
        this.$nextTick(() => this.renderAnalysisChart())
      })
    },
    initChart(id) {
      if (this.charts[id]) {
        this.charts[id].dispose()
      }
      const el = document.getElementById(id)
      if (!el) return null
      this.charts[id] = this.$echarts.init(el, 'macarons')
      return this.charts[id]
    },
    renderGraphChart() {
      const chart = this.initChart('home-chart')
      if (!chart) return

      const labels = this.statCards.map(i => i.label)
      const values = this.statCards.map(i => this.stats[i.key])
      const colors = this.statCards.map(i => i.color)
      const total = values.reduce((a, b) => a + b, 0)
      const echarts = this.$echarts

      const nodes = [{
        id: 'root',
        name: '健康平台',
        value: total,
        symbolSize: 78,
        category: 0,
        itemStyle: {
          color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [
            { offset: 0, color: '#83bff6' },
            { offset: 1, color: '#188df0' }
          ]),
          shadowBlur: 18,
          shadowColor: 'rgba(24,141,240,0.45)'
        },
        label: { show: true, formatter: '健康平台\n总记录 ' + total, fontSize: 13, fontWeight: 'bold', color: '#fff' }
      }]

      this.statCards.forEach((item, i) => {
        const val = values[i] || 0
        nodes.push({
          id: item.key,
          name: item.label,
          value: val,
          symbolSize: Math.max(36, Math.min(72, val * 6 + 28)),
          category: i + 1,
          itemStyle: {
            color: new echarts.graphic.LinearGradient(0, 0, 1, 1, [
              { offset: 0, color: colors[i] },
              { offset: 1, color: colors[i] + 'cc' }
            ]),
            shadowBlur: 10,
            shadowColor: colors[i] + '66'
          },
          label: { show: true, formatter: item.label + '\n' + val + item.unit, fontSize: 11, color: '#444' }
        })
      })

      chart.setOption({
        tooltip: {
          trigger: 'item',
          formatter(params) {
            if (params.dataType === 'edge') return params.data.source + ' → ' + params.data.target
            const d = params.data || {}
            return (d.name || '') + '<br/>数量：' + (d.value != null ? d.value : '-')
          }
        },
        legend: { bottom: 0, type: 'scroll', data: [{ name: '平台核心' }].concat(labels.map(n => ({ name: n }))) },
        animationDuration: 1500,
        series: [{
          type: 'graph',
          layout: 'force',
          data: nodes,
          links: this.statCards.map(item => ({
            source: 'root',
            target: item.key,
            lineStyle: { width: Math.max(1.5, (this.stats[item.key] || 0) * 0.8 + 1), curveness: 0.25, opacity: 0.55 }
          })),
          categories: [{ name: '平台核心' }].concat(labels.map(n => ({ name: n }))),
          roam: true,
          draggable: true,
          focusNodeAdjacency: true,
          force: { repulsion: 320, gravity: 0.12, edgeLength: [90, 180], layoutAnimation: true },
          emphasis: { focus: 'adjacency', lineStyle: { width: 4, opacity: 0.85 } },
          lineStyle: { color: 'source', opacity: 0.45 }
        }]
      })
      this.bindResize()
    },
    renderSunburstChart() {
      const chart = this.initChart('home-sunburst')
      if (!chart) return
      chart.setOption({
        tooltip: { trigger: 'item', formatter: '{b}: {c}' },
        series: [{
          type: 'sunburst',
          radius: ['12%', '90%'],
          sort: 'desc',
          emphasis: { focus: 'ancestor' },
          data: [{
            name: '健康平台',
            children: this.statCards.map(item => ({
              name: item.label,
              value: this.stats[item.key] || 0,
              itemStyle: { color: item.color }
            }))
          }],
          label: { rotate: 'radial', fontSize: 11 },
          itemStyle: { borderRadius: 6, borderWidth: 2, borderColor: '#fff' }
        }]
      })
      this.bindResize()
    },
    renderCommentChart(modules) {
      const chart = this.initChart('home-comment-chart')
      if (!chart) return
      const data = modules.map(m => ({
        name: m.label,
        value: this.commentModuleStats[m.key] || 0
      }))
      chart.setOption({
        tooltip: { trigger: 'item', formatter: '{b}: {c} ({d}%)' },
        legend: { bottom: 0 },
        series: [{
          type: 'pie',
          radius: ['38%', '68%'],
          roseType: 'radius',
          itemStyle: { borderRadius: 8, borderColor: '#fff', borderWidth: 2 },
          label: { formatter: '{b}\n{c}条' },
          data: data
        }]
      })
      this.bindResize()
    },
    renderAnalysisChart() {
      const chart = this.initChart('home-analysis-chart')
      if (!chart) return
      const data = Object.keys(this.analysisDist).map(key => ({
        name: key,
        value: this.analysisDist[key]
      }))
      chart.setOption({
        tooltip: { trigger: 'item' },
        legend: { bottom: 0, type: 'scroll' },
        series: [{
          type: 'pie',
          radius: '65%',
          center: ['50%', '45%'],
          data: data,
          emphasis: {
            itemStyle: { shadowBlur: 12, shadowOffsetX: 0, shadowColor: 'rgba(0,0,0,0.2)' }
          },
          label: { formatter: '{b}\n{d}%' }
        }]
      })
      this.bindResize()
    },
    renderEngageChart() {
      const chart = this.initChart('home-engage-chart')
      if (!chart) return
      chart.setOption({
        tooltip: { trigger: 'axis' },
        legend: { data: ['点击量', '点赞数'], bottom: 0 },
        grid: { left: '3%', right: '4%', bottom: '14%', containLabel: true },
        xAxis: { type: 'category', data: this.engageData.names, axisLabel: { interval: 0, rotate: 20, fontSize: 10 } },
        yAxis: { type: 'value', minInterval: 1 },
        series: [
          {
            name: '点击量',
            type: 'line',
            smooth: true,
            areaStyle: { opacity: 0.25 },
            data: this.engageData.clicks,
            itemStyle: { color: '#409EFF' }
          },
          {
            name: '点赞数',
            type: 'line',
            smooth: true,
            areaStyle: { opacity: 0.2 },
            data: this.engageData.thumbs,
            itemStyle: { color: '#67C23A' }
          }
        ]
      })
      this.bindResize()
    },
    bindResize() {
      window.onresize = () => {
        Object.keys(this.charts).forEach(k => {
          if (this.charts[k]) this.charts[k].resize()
        })
      }
    },
    goPage(path) {
      this.$router.push(path)
    }
  }
}
</script>

<style lang="scss" scoped>
.home-dashboard {
  padding: 10px;
  min-height: 100%;
  background: #f5f7fa;
}
.welcome-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: linear-gradient(135deg, #26c6da 0%, #409eff 100%);
  color: #fff;
  padding: 24px 28px;
  border-radius: 8px;
  margin-bottom: 16px;
}
.welcome-title {
  font-size: 26px;
  font-weight: bold;
  margin-bottom: 8px;
}
.welcome-sub {
  font-size: 14px;
  opacity: 0.9;
}
.welcome-right {
  text-align: right;
}
.welcome-date {
  font-size: 14px;
  opacity: 0.95;
  margin-bottom: 8px;
}
.welcome-badge {
  display: inline-block;
  padding: 4px 12px;
  border-radius: 20px;
  background: rgba(255, 255, 255, 0.2);
  font-size: 13px;
}
.stat-row {
  margin-bottom: 4px;
}
.stat-card {
  background: #fff;
  border-radius: 8px;
  padding: 18px 16px;
  margin-bottom: 16px;
  border-top: 4px solid #409eff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  cursor: pointer;
  transition: transform 0.2s, box-shadow 0.2s;
  &:hover {
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  }
}
.stat-label { font-size: 14px; color: #666; }
.stat-value { font-size: 32px; font-weight: bold; color: #333; margin: 8px 0 4px; }
.stat-unit { font-size: 12px; color: #999; }
.extra-card {
  display: flex;
  align-items: center;
  background: #fff;
  border-radius: 8px;
  padding: 16px;
  margin-bottom: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  cursor: pointer;
  transition: box-shadow 0.2s;
  i { font-size: 32px; margin-right: 14px; }
  &:hover { box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); }
}
.extra-value { font-size: 24px; font-weight: bold; color: #333; }
.extra-label { font-size: 13px; color: #888; margin-top: 4px; }
.panel-card { margin-bottom: 16px; }
.panel-header { font-size: 16px; font-weight: bold; color: #333; }
.home-chart { width: 100%; height: 480px; }
.sub-chart { width: 100%; height: 340px; }
.chart-tip { font-size: 12px; color: #909399; margin: -8px 0 8px 2px; }
.quick-links { display: flex; flex-wrap: wrap; gap: 10px; }
.tip-card {
  background: #fff;
  border-radius: 8px;
  padding: 20px;
  margin-bottom: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  border-left: 4px solid #26c6da;
}
.tip-title {
  font-size: 15px;
  font-weight: bold;
  color: #333;
  margin-bottom: 10px;
  i { color: #26c6da; margin-right: 6px; }
}
.tip-desc { font-size: 13px; color: #666; line-height: 1.7; }
</style>
