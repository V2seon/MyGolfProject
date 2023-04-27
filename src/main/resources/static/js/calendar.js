// 주간 달력 생성 함수
function weekHTML(date, options) {
	// 데이터 검증
	if (date == undefined || date == null || typeof date != "object" || !date instanceof Date) {
		return "";
	}

	setCalendarOptions(options);

	// 달력 연도
	var calendarYear = date.getFullYear();
	// 달력 월
	var calendarMonth = date.getMonth() + 1;
	// 달력 일
	var calendarToday = date.getDate();

	// 달력 현재 요일
	var calendarMonthTodayDay = date.getDay();

	// 주간 날짜 배열
	var arWeek = [null, null, null, null, null, null, null];

	var addDay = 0;
	for (var index = calendarMonthTodayDay; index < 7; index++) {
		arWeek[index] = new Date(calendarYear, calendarMonth - 1, calendarToday + addDay);
		addDay++;
	}

	var addDay = 0;
	for (var index = calendarMonthTodayDay - 1; index >= 0; index--) {
		--addDay;
		arWeek[index] = new Date(calendarYear, calendarMonth - 1, calendarToday + addDay);
	}

	// 오늘
	var today = new Date();

	var html = "";
	html += "<table>";
	if (options.showDay) {
		html += calendarWeekHTML(options);
	}
	html += "<tbody>";
	html += "<tr>";
	var datedayarr = [];
	for (var index = 0; index < 7; index++) {
		var year = arWeek[index].getFullYear();
		var month = arWeek[index].getMonth() + 1;
		var day = arWeek[index].getDate();
		var dateday = year + "-" + (month < 10 ? "0" : "") + month + "-" + (day < 10 ? "0" : "") + day ;
		html += "<td onclick='serchT(this)' name='day' class='day' data-date=\"" + dateday +  "\">";
        datedayarr[index] = dateday;
		html += "<span";
		if (options.showToday && year == today.getFullYear() && month == today.getMonth() + 1
			&& day == today.getDate()) {
			html += " class=\"today\"";
		} else {
			var holiday = false;
			var holidayInfo = getCalendarHoliday(year, month, day);
			if (holidayInfo != undefined && holidayInfo != null) {
				html += " class=\"holiday\"";
				holiday = true;
			}
			if (!holiday) {
				if (index == 0) {
					html += " class=\"sunday\"";
				} else if (index == 6) {
					html += " class=\"saturday\"";
				}
			}
		}
		var holidayInfo = getCalendarHoliday(year, month, day);
		if (holidayInfo != undefined && holidayInfo != null) {
			html += " title=\"" + holidayInfo.title + "\"";
		}
		html += ">" + day + "일</span>";
		html += "</td>";
	}
	html += "<tr><td class='dayT' onclick='serchT(this)' data-date=\"" + datedayarr[0] +  "\" style='color: red;' id='sunp'>&nbsp;</td>"
	html += "<td class='dayT' onclick='serchT(this)' data-date=\"" + datedayarr[1] +  "\" id='monp'>&nbsp;</td>"
	html += "<td class='dayT' onclick='serchT(this)' data-date=\"" + datedayarr[2] +  "\" id='tuep'>&nbsp;</td>"
	html += "<td class='dayT' onclick='serchT(this)' data-date=\"" + datedayarr[3] +  "\" id='wedp'>&nbsp;</td>"
	html += "<td class='dayT' onclick='serchT(this)' data-date=\"" + datedayarr[4] +  "\" id='thup'>&nbsp;</td>"
	html += "<td class='dayT' onclick='serchT(this)' data-date=\"" + datedayarr[5] +  "\" id='frip'>&nbsp;</td>"
	html += "<td class='dayT' onclick='serchT(this)' data-date=\"" + datedayarr[6] +  "\" style='color: blue;' id='satp'>&nbsp;</td></tr>";
	html += "</tbody>";
	html += "</table>";
	return html;
}

// 월간 달력 생성 함수
function calendarHTML(date, options) {
	// 데이터 검증
	if (date == undefined || date == null || typeof date != "object" || !date instanceof Date) {
		return "";
	}

	setCalendarOptions(options);

	// 달력 연도
	var calendarYear = date.getFullYear();
	// 달력 월
	var calendarMonth = date.getMonth() + 1;
	// 달력 일
	var calendarToday = date.getDate();

	var monthLastDate = new Date(calendarYear, calendarMonth, 0);
	// 달력 월의 마지막 일
	var calendarMonthLastDate = monthLastDate.getDate();

	var monthStartDay = new Date(calendarYear, date.getMonth(), 1);
	// 달력 월의 시작 요일
	var calendarMonthStartDay = monthStartDay.getDay();

	// 주 카운트
	var calendarWeekCount = Math.ceil((calendarMonthStartDay + calendarMonthLastDate) / 7);

	// 오늘
	var today = new Date();

	var html = "";
	html += "<table>";
	if (options.showDay) {
		html += calendarWeekHTML(options);
	}
	html += "<tbody>";
	// 위치
	var calendarPos = 0;
	// 날짜
	var calendarDay = 0;
	for (var index1 = 0; index1 < calendarWeekCount; index1++) {
		html += "<tr>";
		for (var index2 = 0; index2 < 7; index2++) {
			html += "<td";
			if (calendarMonthStartDay <= calendarPos && calendarDay < calendarMonthLastDate) {
				calendarDay++;
				html += " data-date=\"" + calendarYear + "-" + (calendarMonth < 10 ? "0" : "") + calendarMonth + "-" + (calendarDay < 10 ? "0" : "") + calendarDay +  "\">";
				html += "<span";
				if (options.showToday && calendarYear == today.getFullYear() && calendarMonth == today.getMonth() + 1
					&& calendarDay == today.getDate()) {
					html += " class=\"today\"";
				} else {
					var holiday = false;
					var holidayInfo = getCalendarHoliday(calendarYear, calendarMonth, calendarDay);
					if (holidayInfo != undefined && holidayInfo != null) {
						html += " class=\"holiday\"";
						holiday = true;
					}
					if (!holiday) {
						if (index2 == 0) {
							html += " class=\"sunday\"";
						} else if (index2 == 6) {
							html += " class=\"saturday\"";
						}
					}
				}
				var holidayInfo = getCalendarHoliday(calendarYear, calendarMonth, calendarDay);
				if (holidayInfo != undefined && holidayInfo != null) {
					html += " title=\"" + holidayInfo.title + "\"";
				}
				html += ">" + calendarDay + "</span>";
			} else {
				html += ">";
			}
			html += "</td>";
			calendarPos++;
		}
		html += "</tr>";
	}
	html += "</tbody>";
	html += "</table>";
	return html;
}

// 달력 공휴일 함수
function getCalendarHoliday(calendarYear, calendarMonth, calendarDay) {
	if (Object.keys(hashmapTemporaryHoliday).length == 0) {
		return null;
	}

	// 공휴일(임시 공휴일 포함)
	var holidayInfo = hashmapTemporaryHoliday[calendarYear + "-" + calendarMonth + "-" + calendarDay];
	if (holidayInfo == undefined || holidayInfo == null) {
		holidayInfo = hashmapTemporaryHoliday[calendarMonth + "-" + calendarDay];
	}
	return holidayInfo ;
}

// 기본값 처리
function setCalendarOptions(options) {
	// 기본값 처리
	if (options.showDay == undefined || options.showDay == null || typeof options.showDay != "boolean") {
		options.showDay = true;
	}
	if (options.showFullDayName == undefined || options.showFullDayName == null || typeof options.showFullDayName != "boolean") {
		options.showFullDayName = false;
	}
	if (options.showToday == undefined || options.showToday == null || typeof options.showToday != "boolean") {
		options.showToday = true;
	}

	// 공휴일 처리
	if (options.arHoliday != undefined && options.arHoliday != null && Array.isArray(options.arHoliday)) {
		Object.assign(hashmapTemporaryHoliday, options.arHoliday);
	}
}

// 달력 요일 HTML
function calendarWeekHTML(options) {
	var html = "<thead><tr>";
	for (var index = 0; index < calendarDays.length; index++) {
		html += "<th";
		if (index == 0) {
			html += " class=\"sunday\"";
		} else if (index == 6) {
			html += " class=\"saturday\"";
		}
		html += ">" + calendarDays[index];
		if (options.showFullDayName) {
			html += "요일";
		}
		html += "</th>";
	}
	html += "</tr></thead>";
	return html;
}

// 달력 인원수 검색 함수
function seRec(day){
let sendData= {
    'day' :day
}

$.ajax({
            url : "/SelPerson",
            data : sendData,
            type : "POST",
            success : function(result){
                document.getElementById('sunp').innerText = result.Sun ;
                document.getElementById('monp').innerText = result.Mon ;
                document.getElementById('tuep').innerText = result.Tue ;
                document.getElementById('wedp').innerText = result.Wed ;
                document.getElementById('thup').innerText = result.Thu ;
                document.getElementById('frip').innerText = result.Fri ;
                document.getElementById('satp').innerText = result.Sat ;
            },
            error: function (e) {
            }
        });
}