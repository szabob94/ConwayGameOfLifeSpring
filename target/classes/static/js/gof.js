$( document ).ready(function() {
    console.log( "ready!" );
    drawMap();

    $('div.cell').bind('click', function() {
        $(this).toggleClass('black');
    });

    var runGameBtn = $('#runGame');
    runGameBtn.bind('click', function() {

        if (runGameBtn.text() == 'Start!')
            runGameBtn.text('Pause');
        else {
            runGameBtn.text('Start!');
        }
        if (isGameRunning == false){
            sendAjaxRequestAutomatically();
            isGameRunning = true
        } else {
            isGameRunning = false;
        }
    });
    var nextTurnBtn = $('#nextTurnBtn');
    nextTurnBtn.bind('click', function() {
        sendAjaxRequestManually();
    });
});

var isGameRunning = false;
function drawMap() {
	var gofGrid = $('div#gof_grid');
	for (i = 0; i < 100; i++) {
		gofGrid.append('<div class="row'+i+'" col-md-12">')
		var rowI = $('div.row'+i);
		for (j = 0; j < 100; j++) {
			rowI.append('<div class="cell"' + ' x=' + j + ' y=' + i + ' />');
		}
        gofGrid.append('<div/>');
	}
}

function getJsonFromPaintedCells() {
    var jsonObject = {};
    jsonObject.cellList = [];

    var cells = $('.cell.black');
    $.each(cells, function(index, element) {
        var e = {};
    	e.x = parseInt($(element).attr('x'));
    	e.y = parseInt($(element).attr('y'));
    	jsonObject.cellList.push(e);
    });
    return JSON.stringify(jsonObject);
}

function sendAjaxRequestAutomatically() {
   var paintedCells = getJsonFromPaintedCells();
   $.ajax({
        url : '/json/gamemechanism',
        type : 'post',
        data : paintedCells,
        contentType: 'application/json; charset=utf-8',
        dataType : 'json',
        success : function(newGameState) {
        	if (isGameRunning == true){
                console.log("recalculatedStructure: " + newGameState, newGameState);
                repaintGameState(newGameState);
                setTimeout(sendAjaxRequestAutomatically, 250);
        	}
        }
   });
}

function sendAjaxRequestManually() {
    var paintedCells = getJsonFromPaintedCells();
    $.ajax({
        url : '/json/gamemechanism',
        type : 'post',
        data : paintedCells,
        contentType: 'application/json; charset=utf-8',
        dataType : 'json',
        success : function(newGameState) {
            if (isGameRunning == false){
                console.log("recalculatedStructure: " + newGameState, newGameState);
                repaintGameState(newGameState);
            }
        }
    });
}

function repaintGameState(structure) {
    $('.cell.black').removeClass('black');
    $.each(structure.cellList, function(index, element) {
        $('.cell[x=' + element.x + '][y=' + element.y + ']').addClass('black');
    });
}


