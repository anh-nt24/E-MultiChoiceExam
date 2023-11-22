document.addEventListener("DOMContentLoaded", function () {
	var currentPath = window.location.pathname;
	if (currentPath.split('/').length > 1) {
		currentPath = '/' + currentPath.split('/')[1];
	}
	console.log(currentPath);
	var navItems = document.querySelectorAll("#home-nav li");
	console.log(navItems);

	navItems.forEach(function (item) {
		var link = item.querySelector("a");
		if (link) {
			var href = link.getAttribute("href");
			console.log(href)
			if (currentPath === href) {
				item.classList.add("active");
			}
		}
	});
});