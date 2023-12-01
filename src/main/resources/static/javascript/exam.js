var default_btn = document.getElementById("default_btn")
var simple_btn = document.getElementById("simple_btn")
var q_section = document.getElementById("q_section")

default_btn.addEventListener("click",()=>{
    var default_layout = `<div id = "question_layout" th:replace="fragments/student/question_layout.html :: div#default_exam_layout"></div>`

    var question_layout = document.getElementById("question_layout")
    if(question_layout == null){
        q_section.innerHTML += default_layout;
    }else{
        question_layout.remove()
        q_section.innerHTML += default_layout;
    }
    
})

default_btn.addEventListener("click",()=>{
    var simple_layout = `<div id = "question_layout" th:replace="fragments/student/question_layout.html :: div#simple_exam_layout"></div>`

    var question_layout = document.getElementById("question_layout")
    if(question_layout == null){
        q_section.innerHTML += simple_layout;
    }else{
        question_layout.remove()
        q_section.innerHTML += simple_layout;
    }
})
$(document).ready(function(){
    function checkToken(){
        
    }
}) 

