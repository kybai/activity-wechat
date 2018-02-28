/**
 * bs_pagination simple localization - ENGLISH
 *
 * DO NOT CHANGE this file, as it will be overwritten in next update.
 * To use different values, write and use a similar structure js file.
 *
 */
var rsc_bs_pag = {
    go_to_page_title: 'Go to page',
    rows_per_page_title: 'Rows per page',
    current_page_label: 'Page',
    current_page_abbr_label: 'p.',
    total_pages_label: 'of',
    total_pages_abbr_label: '/',
    total_rows_label: 'of',
    rows_info_records: 'records',
    go_top_text: '&laquo;',
    go_prev_text: '&larr;',
    go_next_text: '&rarr;',
    go_last_text: '&raquo;'
};

function loadPagination(data, pageID, initPageFunc, loadPageFunc) {
    var pageId = "#" + pageID;
    $(pageId).bs_pagination({
        currentPage: data.pageNum,
        totalPages: data.pages,
        totalRows: data.total,
        rowsPerPage: data.size,
        visiblePageLinks: 5,
        showGoToPage: false,
        showRowsPerPage: false,
        showRowsInfo: false,
        showRowsDefaultInfo: false,
        navListContainerClass: "col-xs-12 col-sm-12 col-md-12",//使分页按钮区域占一整行
        containerClass: "",//包含按钮的class为空，原为well，有背景
        onLoad: function (event, pageData) {//分页按钮加载时
            loadPageFunc(data.list);
            $(pageId + " " + "#nav_list_" + pageId.split("#")[1] + " li:last-child").after("<li><span class='page-total-color' style='border:0;'>共" + data.total + "条</span></li>")
        },
        onChangePage: function (event, pageData) {//分页按钮改变时
            initPageFunc(pageData.currentPage);
        }
    });
}