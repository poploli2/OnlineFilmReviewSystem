document.addEventListener('DOMContentLoaded', function() {
    // 获取所有星级评分元素
    var starRatings = document.querySelectorAll('.star-rating');

    // 遍历每个元素并设置星级评分
    starRatings.forEach(function(starRating) {
        // 获取原始评分值
        var score = parseFloat(starRating.dataset.score);
        // 转换评分为星星
        starRating.innerHTML = convertScoreToStars(score);
    });
});

// 将分数转换为星星的函数
function convertScoreToStars(score) {
    var starsFull = '<span class="star full">★</span>';
    var starsHalf = '<span class="star half">☆</span>';
    var starsEmpty = '<span class="star empty">☆</span>';
    var stars = '';

    // 计算满星、半星和空星的数量
    var fullStars = Math.floor(score / 2);
    var halfStars = score % 2;
    var emptyStars = 5 - fullStars - Math.ceil(halfStars);

    // 添加满星
    for (var i = 0; i < fullStars; i++) {
        stars += starsFull;
    }

    // 如果有半星，则添加
    if (halfStars) {
        stars += starsHalf;
    }

    // 添加空星
    for (var j = 0; j < emptyStars; j++) {
        stars += starsEmpty;
    }

    return stars;
}
