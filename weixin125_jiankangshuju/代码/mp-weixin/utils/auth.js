function clearSession() {
  wx.removeStorageSync('token')
  wx.removeStorageSync('userid')
  wx.removeStorageSync('nickname')
  wx.removeStorageSync('nowTable')
  wx.removeStorageSync('role')
}

function goLogin(msg) {
  clearSession()
  if (msg) {
    wx.showToast({ title: msg, icon: 'none', duration: 2000 })
  }
  setTimeout(function () {
    wx.reLaunch({ url: '/pages/login/login' })
  }, msg ? 800 : 0)
}

function handleUnauthorized() {
  goLogin('请先登录')
}

function handleDisabled(msg) {
  goLogin(msg || '账号已被禁用，请联系管理员')
}

module.exports = {
  clearSession: clearSession,
  goLogin: goLogin,
  handleUnauthorized: handleUnauthorized,
  handleDisabled: handleDisabled
}
