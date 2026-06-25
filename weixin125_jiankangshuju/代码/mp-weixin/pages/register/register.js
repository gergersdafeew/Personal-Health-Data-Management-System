var request = require('../../utils/request.js')

Page({
  data: {
    yonghuzhanghao: '',
    mima: '',
    yonghuming: '',
    xingbie: '男',
    lianxidianhua: '',
    xingbieOptions: ['男', '女'],
    xingbieIndex: 0
  },

  onLoad: function () {
    this.setData({ xingbie: '男' })
  },

  onInput: function (e) {
    var field = e.currentTarget.dataset.field
    var data = {}
    data[field] = e.detail.value
    this.setData(data)
  },

  onXingbieChange: function (e) {
    var index = Number(e.detail.value)
    this.setData({
      xingbieIndex: index,
      xingbie: this.data.xingbieOptions[index]
    })
  },

  onRegister: function () {
    var form = {
      yonghuzhanghao: (this.data.yonghuzhanghao || '').trim(),
      mima: this.data.mima,
      yonghuming: this.data.yonghuming,
      xingbie: this.data.xingbie || this.data.xingbieOptions[this.data.xingbieIndex],
      lianxidianhua: this.data.lianxidianhua,
      zhuangtai: '正常'
    }

    if (!form.yonghuzhanghao) {
      wx.showToast({ title: '请输入用户账号', icon: 'none' })
      return
    }
    if (!form.mima) {
      wx.showToast({ title: '请输入密码', icon: 'none' })
      return
    }
    if (form.lianxidianhua && !/^1[3-9]\d{9}$/.test(form.lianxidianhua)) {
      wx.showToast({ title: '请输入正确的手机号', icon: 'none' })
      return
    }

    request.request({
      url: 'yonghu/register',
      method: 'POST',
      data: form
    }).then(function () {
      wx.showToast({ title: '注册成功', icon: 'success' })
      setTimeout(function () {
        wx.navigateTo({ url: '/pages/login/login' })
      }, 1500)
    })
  },

  goLogin: function () {
    wx.navigateTo({ url: '/pages/login/login' })
  }
})
