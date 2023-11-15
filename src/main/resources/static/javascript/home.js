document.addEventListener("DOMContentLoaded", function () {
	var currentPath = window.location.pathname;
	var navItems = document.querySelectorAll("#home-nav li");

	navItems.forEach(function (item) {
		var link = item.querySelector("a");
		var href = link.getAttribute("href");

		if (currentPath === href) {
			item.classList.add("active");
		}
	});
});