var config = require('./config.js')
var auth = require('./auth.js')

function request(options) {
  return new Promise(function (resolve, reject) {
    wx.request({
      url: config.baseUrl + options.url,
      method: options.method || 'GET',
      data: options.data || {},
      header: Object.assign({
        'Content-Type': 'application/json;charset=UTF-8',
        Token: wx.getStorageSync('token') || ''
      }, options.header || {}),
      success: function (res) {
        if (res.statusCode === 200 && res.data) {
          if (res.data.code === 0) {
            resolve(res.data)
          } else if (res.data.code === 401) {
            auth.handleUnauthorized()
            reject(res.data)
          } else if (res.data.code === 403) {
            auth.handleDisabled(res.data.msg)
            reject(res.data)
          } else {
            wx.showToast({ title: res.data.msg || '请求失败', icon: 'none' })
            reject(res.data)
          }
        } else {
          wx.showToast({
            title: res.statusCode ? '请求失败(' + res.statusCode + ')' : '网络异常',
            icon: 'none'
          })
          reject(res)
        }
      },
      fail: function () {
        wx.showToast({ title: '网络异常，请检查后端是否启动', icon: 'none' })
        reject(new Error('network error'))
      }
    })
  })
}

function uploadFile(filePath) {
  return new Promise(function (resolve, reject) {
    wx.uploadFile({
      url: config.baseUrl + 'file/upload',
      filePath: filePath,
      name: 'file',
      header: { Token: wx.getStorageSync('token') || '' },
      success: function (res) {
        try {
          var data = JSON.parse(res.data)
          if (data.code === 0) {
            resolve({
              url: config.baseUrl + 'upload/' + data.file,
              file: data.file
            })
          } else {
            wx.showToast({ title: data.msg || '上传失败', icon: 'none' })
            reject(data)
          }
        } catch (e) {
          reject(e)
        }
      },
      fail: reject
    })
  })
}

module.exports = {
  request: request,
  uploadFile: uploadFile,
  BASE_URL: config.baseUrl
}
