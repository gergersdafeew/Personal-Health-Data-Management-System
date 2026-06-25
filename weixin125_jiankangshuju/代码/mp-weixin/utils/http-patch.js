(function () {
  var originRequest = wx.request

  function clearSession() {
    wx.removeStorageSync('token')
    wx.removeStorageSync('userid')
    wx.removeStorageSync('nickname')
    wx.removeStorageSync('nowTable')
    wx.removeStorageSync('role')
  }

  wx.request = function (options) {
    var originComplete = options.complete
    var originSuccess = options.success

    options.complete = function (res) {
      if (res && res.data && typeof res.data === 'object') {
        if (res.data.code === 403) {
          clearSession()
          wx.showToast({
            title: res.data.msg || '账号已被禁用，请联系管理员',
            icon: 'none',
            duration: 2000
          })
          setTimeout(function () {
            wx.reLaunch({ url: '/pages/login/login' })
          }, 1800)
        } else if (res.data.code === 401) {
          clearSession()
          wx.reLaunch({ url: '/pages/login/login' })
        }
      }
      if (typeof originComplete === 'function') {
        originComplete(res)
      }
    }

    if (typeof originSuccess === 'function') {
      options.success = originSuccess
    }

    return originRequest.call(wx, options)
  }
})()
