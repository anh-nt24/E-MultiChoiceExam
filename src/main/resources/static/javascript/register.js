function readURL(input) {
      if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function (e) {
			  	$('#blah').attr('src', e.target.result);
			};
			reader.readAsDataURL(input.files[0]);
      }
}

function showField() {
	const role = document.getElementById('role').value;
	const fields = document.getElementById('field');
	if (role == 'teacher') {
		fields.innerHTML = `
			<div class="form-group">
				<label for="degree">Degree:</label>
				<input type="text" class="form-control" id="degree" aria-describedby="emailHelp" name="degree" required>
			</div>
			<div class="form-group">
				<label for="edbg">Educational background:</label>
				<textarea type="text" class="form-control" id="edbg" aria-describedby="emailHelp" name="edbg" required></textarea>
			</div>
			<div class="form-group">
				<label for="field">Field:</label>
				<input type="text" class="form-control" id="field" aria-describedby="emailHelp" name="field" required>
			</div>
			<div class="form-group">
				<label for="position">Position:</label>
				<input type="text" class="form-control" id="position" aria-describedby="emailHelp" name="position" required>
			</div>
			<div class="form-group">
				<label for="faculty">Faculty:</label>
				<input type="text" class="form-control" id="faculty" aria-describedby="emailHelp" name="faculty" required>
			</div>
		`;
	} else if (role === 'student') {
		fields.innerHTML = `
			<div class="form-group">
				<label for="studentid">Student ID:</label>
				<input type="text" class="form-control" id="studentid" aria-describedby="emailHelp" name="studentid" required>
			</div>
			<div class="form-group">
				<label for="year">Enrollment year:</label>
				<input type="number" class="form-control" id="year" aria-describedby="emailHelp" name="year" required>
			</div>
			<div class="form-group">
				<label for="major">Major:</label>
				<input type="text" class="form-control" id="major" aria-describedby="emailHelp" name="major" required>
			</div>
		`;
	} else {
		fields.innerHTML = '';
	}
}