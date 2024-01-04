document.addEventListener('DOMContentLoaded', function(event) {
    // 获取当前URL的路径
    const currentPath = window.location.pathname;

    // 默认排序方式
    const defaultSort = '/movies/popularity';
    const defaultPath = '/movies';

    // 获取所有的排序链接
    const sortLinks = document.querySelectorAll('.sort-options a');

    // 设置默认排序链接的活跃状态
    const setActive = function(link, path) {
        if (currentPath.endsWith(path) || currentPath === defaultPath && path === defaultSort) {
            link.classList.add('active');
        } else {
            link.classList.remove('active');
        }
    };

    // 循环每个链接，检查它的href属性是否包含当前URL的路径
    sortLinks.forEach(function(link) {
        setActive(link, link.getAttribute('href'));
    });
});