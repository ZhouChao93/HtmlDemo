const { app, BrowserWindow } = require('electron')
// 在文件头部引入 Node.js 中的 path 模块
const path = require('path')
// 创建一个窗口
function createWindow () {
    const win = new BrowserWindow({
        width: 800,
        height: 600,
        webPreferences: {
            //__dirname 字符串指向当前正在执行脚本的路径 (在本例中，它指向你的项目的根文件夹)
            //path.join API 将多个路径联结在一起，创建一个跨平台的路径字符串。
            preload: path.join(__dirname, 'preload.js')
        }
    })

    win.loadFile('index.html')
}
// 在 Electron 中，只有在 app 模块的 ready 事件被激发后才能创建浏览器窗口
// app.whenReady() API来监听此事件
app.whenReady().then(() => {
    createWindow()
    /**
     * 当 Linux 和 Windows 应用在没有窗口打开时退出了，macOS 应用通常即使在没有打开任何窗口的情况下也继续运行，并且在没有窗口可用的情况下激活应用时会打开新的窗口。
     * 为了实现这一特性，监听 app 模块的 activate 事件。如果没有任何浏览器窗口是打开的，则调用 createWindow() 方法。
     * 因为窗口无法在 ready 事件前创建，你应当在你的应用初始化后仅监听 activate 事件。 通过在您现有的 whenReady() 回调中附上您的事件监听器来完成这个操作
     */
    app.on('activate', function () {
        if (BrowserWindow.getAllWindows().length === 0) createWindow()
    })
})
//关闭所有窗口时退出应用
app.on('window-all-closed', function () {
    if (process.platform !== 'darwin') app.quit()
})