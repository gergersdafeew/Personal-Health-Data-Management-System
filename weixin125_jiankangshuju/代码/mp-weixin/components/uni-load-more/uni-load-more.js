Component({
  properties: {
    status: {
      type: String,
      value: 'more'
    },
    showIcon: {
      type: Boolean,
      value: true
    },
    iconType: {
      type: String,
      value: 'auto'
    },
    iconSize: {
      type: Number,
      value: 24
    },
    color: {
      type: String,
      value: '#777777'
    },
    contentText: {
      type: Object,
      value: {
        contentdown: '上拉显示更多',
        contentrefresh: '正在加载...',
        contentnomore: '没有更多数据了'
      }
    }
  },
  data: {
    webviewHide: false,
    platform: 'android'
  },
  lifetimes: {
    attached() {
      try {
        const info = wx.getSystemInfoSync()
        this.setData({ platform: info.platform || 'android' })
      } catch (e) {}
    }
  },
  methods: {
    onClick() {
      this.triggerEvent('clickLoadMore', { status: this.data.status })
    }
  }
})
