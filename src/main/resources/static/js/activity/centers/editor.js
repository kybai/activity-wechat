var editor = UE.getEditor('description', {
    serverUrl: base + '/ueditor/config' // 服务器统一请求接口路径
});

UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
UE.Editor.prototype.getActionUrl = function (action) {
    if (action === 'uploadimage') {
        return base + '/ueditor/upload';
    } else {
        return this._bkGetActionUrl.call(this, action);
    }
};

function getUEContent() {
    var ue = UE.getEditor('description');
    return ue.getContent();
}