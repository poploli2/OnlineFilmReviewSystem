// document.addEventListener("DOMContentLoaded", function() {
//     const slides = document.querySelectorAll(".carousel .slide");
//     let currentSlide = 0;
//
//     // 初始化，只显示第一张图片
//     slides.forEach((slide, index) => {
//         slide.style.display = index === 0 ? "block" : "none";
//     });
//
//     function nextSlide() {
//         slides[currentSlide].style.display = "none";
//         currentSlide = (currentSlide + 1) % slides.length;
//         slides[currentSlide].style.display = "block";
//     }
//
//     setInterval(nextSlide, 5000); // 每5秒切换一次
// });

document.addEventListener("DOMContentLoaded", function() {
    const slides = document.querySelectorAll(".carousel .slide");
    let currentSlide = 0;

    function showSlide(index) {
        slides.forEach(slide => slide.style.display = "none");
        slides[index].style.display = "block";
    }

    function nextSlide() {
        currentSlide = (currentSlide + 1) % slides.length;
        showSlide(currentSlide);
    }

    function prevSlide() {
        currentSlide = (currentSlide - 1 + slides.length) % slides.length;
        showSlide(currentSlide);
    }

    document.querySelector(".carousel-control.next").addEventListener("click", nextSlide);
    document.querySelector(".carousel-control.prev").addEventListener("click", prevSlide);

    showSlide(currentSlide); // 初始化显示第一张幻灯片
    setInterval(nextSlide, 5000); // 每5秒自动切换到下一张
});
