var formHasBeenSubmitted = false;

$(document).ready(function () {
    $("#submit").click(function () {
        var name = $("#nameID").val();
        var sectors = $("#sectorsID").val();
        var terms = $('#agreeID:checked').length > 0;
        var agree = $("#agreeID").val();
        var submitMessagePart = "Form successfully submitted!";
        var changeSubmissionMessagePart = "\n\nIf you wish to change your submission, you can make the necessary " +
            "changes and resubmit your answer.";

        if (!name || sectors == "" || !terms) {
            alert("All fields are mandatory!");
        } else {
            if (!formHasBeenSubmitted) {
                formHasBeenSubmitted = true;
                submit(name, sectors, agree);
            } else {
                submitMessagePart = "Form successfully resubmitted!"
                reSubmit(name, sectors, agree);
            }
            alert(submitMessagePart + changeSubmissionMessagePart);
        }

    });
});


$(window).on('load', function () {
    var spaces = "";
    $.ajax({
        url: 'http://localhost:8080/JavaEEHelloWorld_war_exploded/loadSelectBox',
        datatype: "JSON",
        type: "GET",
        success: function (result) {
            var sectors = $("#sectorsID");
            $.each(result, function () {
                for (var i = 0; i < this.path.length - 1; i++) {
                    spaces += "&emsp;"
                }
                sectors.append($("<option />").val(this.id).html(spaces + this.sector));
                spaces = "";
            });
        }
    });
});


function reSubmit(name, sectors, agree) {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/JavaEEHelloWorld_war_exploded/resubmit',
        data: {
            name: name,
            sectors: sectors,
            agree: agree
        },
        success: function () {
            refillForm();
        }
    })
}


function submit(name, sectors, agree) {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/JavaEEHelloWorld_war_exploded/submit',
        data: {
            name: name,
            sectors: sectors,
            agree: agree
        },
        success: function () {
            refillForm();
        }
    })
}


function refillForm() {
    $.ajax({
        url: 'http://localhost:8080/JavaEEHelloWorld_war_exploded/refill',
        datatype: "JSON",
        type: "GET",
        success: function (result) {
            console.log(result.name + result.sectors);
            document.getElementById("nameID").value = result.name + "YEEHEE";
            document.getElementById("agreeID").checked = false;
        }
    });
}