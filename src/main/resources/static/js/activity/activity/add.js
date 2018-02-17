$(function () {
    initData();
});

function initData() {
    laydate.render({
        elem: '#beginTime'
    });
    laydate.render({
        elem: '#endTime'
    });
}