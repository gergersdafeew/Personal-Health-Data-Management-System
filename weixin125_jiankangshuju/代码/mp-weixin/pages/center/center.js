var request = require('../../utils/request.js')
var auth = require('../../utils/auth.js')
var menu = require('../../utils/menu.js')

Page({
  data: {
    user: {},
    menuItems: []
  },

  onShow: function () {
    this.loadData()
  },

  loadData: function () {
    var token = wx.getStorageSync('token')
    if (!token) {
      auth.goLogin('请先登录')
      return
    }
    var tableName = wx.getStorageSync('nowTable') || 'yonghu'
    var role = wx.getStorageSync('role') || '用户'
    var menuList = menu.list()
    var menuItems = []

    menuList.forEach(function (item) {
      if (item.roleName === role && item.backMenu) {
        item.backMenu.forEach(function (group) {
          if (group.child) {
            group.child.forEach(function (child) {
              menuItems.push(child)
            })
          }
        })
      }
    })

    request.request({ url: tableName + '/session', method: 'GET' }).then(function (res) {
      var user = res.data || {}
      if (!user.zhuangtai) user.zhuangtai = '正常'
      if (user.zhuangtai === '禁用') {
        auth.handleDisabled()
        return
      }
      this.setData({ user: user, menuItems: menuItems })
    }.bind(this)).catch(function () {})
  },

  goUserInfo: function () {
    wx.navigateTo({ url: '/pages/user-info/user-info' })
  },

  goPage: function (e) {
    var url = e.currentTarget.dataset.url
    if (!url) return
    wx.navigateTo({
      url: url,
      fail: function () {
        wx.switchTab({ url: url })
      }
    })
  },

  onLogout: function () {
    wx.showModal({
      title: '提示',
      content: '确定退出登录吗？',
      success: function (res) {
        if (res.confirm) auth.goLogin()
      }
    })
  }
})
