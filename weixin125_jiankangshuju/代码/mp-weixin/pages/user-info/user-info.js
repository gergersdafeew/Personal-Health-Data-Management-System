var request = require('../../utils/request.js')
var auth = require('../../utils/auth.js')

Page({
  data: {
    ruleForm: {
      yonghuzhanghao: '',
      mima: '',
      yonghuming: '',
      xingbie: '',
      lianxidianhua: '',
      gerentouxiang: '',
      zhuangtai: '正常'
    },
    xingbieOptions: ['男', '女'],
    xingbieIndex: 0
  },

  onShow: function () {
    this.loadUser()
  },

  loadUser: function () {
    var tableName = wx.getStorageSync('nowTable') || 'yonghu'
    if (!wx.getStorageSync('token')) {
      auth.goLogin('请先登录')
      return
    }
    request.request({ url: tableName + '/session', method: 'GET' }).then(function (res) {
      var user = res.data || {}
      var xingbieIndex = user.xingbie === '女' ? 1 : 0
      if (!user.zhuangtai) user.zhuangtai = '正常'
      this.setData({ ruleForm: user, xingbieIndex: xingbieIndex })
    }.bind(this))
  },

  onInput: function (e) {
    var field = e.currentTarget.dataset.field
    var key = 'ruleForm.' + field
    var data = {}
    data[key] = e.detail.value
    this.setData(data)
  },

  onXingbieChange: function (e) {
    var index = Number(e.detail.value)
    this.setData({
      xingbieIndex: index,
      'ruleForm.xingbie': this.data.xingbieOptions[index]
    })
  },

  chooseAvatar: function () {
    var that = this
    wx.chooseImage({
      count: 1,
      sizeType: ['compressed'],
      sourceType: ['album', 'camera'],
      success: function (res) {
        request.uploadFile(res.tempFilePaths[0]).then(function (result) {
          that.setData({ 'ruleForm.gerentouxiang': result.url || result.file })
        })
      }
    })
  },

  onSave: function () {
    var tableName = wx.getStorageSync('nowTable') || 'yonghu'
    var form = this.data.ruleForm
    if (form.zhuangtai === '禁用') {
      wx.showToast({ title: '账号已禁用，无法修改资料', icon: 'none' })
      return
    }
    if (!form.mima) {
      wx.showToast({ title: '密码不能为空', icon: 'none' })
      return
    }
    request.request({
      url: tableName + '/update',
      method: 'POST',
      data: form
    }).then(function () {
      wx.showToast({ title: '保存成功', icon: 'success' })
      wx.setStorageSync('nickname', form.yonghuming || form.yonghuzhanghao)
    })
  },

  onLogout: function () {
    wx.showModal({
      title: '提示',
      content: '确定退出登录吗？',
      success: function (res) {
        if (res.confirm) {
          auth.goLogin()
        }
      }
    })
  }
})
