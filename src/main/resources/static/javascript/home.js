document.addEventListener("DOMContentLoaded", function () {
	var currentPath = window.location.pathname;
	if (currentPath.split('/').length > 1) {
		currentPath = '/' + currentPath.split('/')[1];
	}
	var navItems = document.querySelectorAll("#home-nav li");

	navItems.forEach(function (item) {
		var link = item.querySelector("a");
		if (link) {
			var href = link.getAttribute("href");
			if (currentPath === href) {
				item.classList.add("active");
			}
		}
	});

	var flashElement = document.getElementById('flashMessage');
	console.log(flashElement);

	if (flashElement) {
		// Apply the animation class
		flashElement.classList.add("animate--drop-in-fade-out");

		// Remove the animation class after 2 seconds (adjust as needed)
		setTimeout(function() {
			flashElement.classList.remove("animate--drop-in-fade-out");
		}, 2000);
	}

});

function confirmDelete() {
    var confirmDelete = confirm("Are you sure you want to delete this?");
    return confirmDelete;
}

function confirmUpdate() {
    var confirmDelete = confirm("Are you sure you want to update this?");
    return confirmDelete;
}


