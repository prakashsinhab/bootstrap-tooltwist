function Pager(tableName, itemsPerPage) {
	this.tableName = tableName;
	this.itemsPerPage = itemsPerPage;
	this.currentPage = 1;
	this.pages = 0;
	this.records = 0;
	this.inited = false;
	this.element = new Array();
	this.pagerName;
	this.naviNumber;
	this.showSelection = true;
	this.selection = '<div>Show<select onchange="loadData(this.value)"><option value="10">10</option><option value="20">20</option><option value="50">50</option><option value="100">100</option></select>per page.</div>';
	this.naviType;
	this.naviClass;
	this.showRecords = function(from, to) {
		var rows = document.getElementById(this.tableName).rows;
		// i starts from 1 to skip table header row
		for ( var i = 0; i < rows.length; i++) {
			if (i < from || i > to)
				rows[i].style.display = 'none';
			else
				rows[i].style.display = '';
		}
	};
	this.showPage = function(pageNumber) {
		if (!this.inited) {
			alert("not inited");
			return;
		}
		try {
			// var oldPageAnchor =
			// document.getElementById('pg'+this.currentPage);
			// oldPageAnchor.className = 'pg-normal';
		} catch (err) {
		}
		this.currentPage = pageNumber;
		try {
			// var newPageAnchor =
			// document.getElementById('pg'+this.currentPage);
			// newPageAnchor.className = 'pg-selected';
		} catch (err) {
		}
		var from = (pageNumber - 1) * itemsPerPage;// + 1;
		var to = parseInt(from) + parseInt(itemsPerPage - 1);
		this.showRecords(from, to);
		this.filterNavi();
	};
	this.prev = function() {
		if (this.currentPage > 1) {
			this.showPage(this.currentPage - 1);
			this.filterNavi();
		}
	};
	this.next = function() {
		if (this.currentPage < this.pages) {
			this.showPage(this.currentPage + 1);
			this.filterNavi();
		}
	};
	this.init = function() {
		var rows = document.getElementById(this.tableName).rows;
		var records = (rows.length); // - 1); <-- Header count set
		this.records = records;
		this.pages = Math.ceil(records / itemsPerPage);
		this.inited = true;
	};
	this.showPageNav = function(pager, positionId, pageNum) {
		if (!this.inited) {
			alert("not inited");
			return;
		}
		this.pagerName = pager;
		var tmpId = positionId.split(",");
		for ( var i = 0; i < tmpId.length; i++) {
			this.element[i] = document.getElementById(tmpId[i]);
		}
		this.naviNumber = pageNum;
		this.filterNavi();
	};
	this.filterNavi = function() {
//		if (this.pages <= 1)
//			return;
		var counter = 1;
		var crntPage = 1;
		var num = this.naviNumber;
		if (this.pages - this.currentPage < num - 1 && this.pages > num) {
			crntPage = this.pages - (num - 1);
		} else if (this.pages > 10) {
			crntPage = this.currentPage;
		}
		var pagerHtml = "";
		if (this.naviType == 'TYPE_QV') {
			
			var prevClass = "";
			var nextClass = "";
			var curTot = this.currentPage * this.itemsPerPage;
			if (curTot > this.records)
				curTot = this.records;
			
			crntPage = this.currentPage;
			
			console.log(crntPage + " = " + num  + " = " + (this.records / this.itemsPerPage) + " = " + this.pagerName);
			if(crntPage >= (this.records / this.itemsPerPage)) {
				nextClass = "innactive";
			}
			if(crntPage == 1) {
				prevClass = "innactive";
			}
			else if(crntPage > 1) {
				crntPage--;
			}
			
			if(crntPage + num - 1 >= (this.records / this.itemsPerPage)) {
				crntPage = (this.records / this.itemsPerPage) - 1;
				crntPage = parseInt(crntPage);
			}
			
			if(crntPage < 1 && num > 1) {
				crntPage = 1;
			}
			
			
			pagerHtml += '<div class="col-md-4 col-xs-4 center"><a class="prev '+prevClass+'" onclick="' + this.pagerName + '.prev();"><i class="fa fa-chevron-left push-right-5"></i>Previous</a></div>';
			pagerHtml += '<div class="col-md-4 col-xs-4 center"><ul>';
			
			for ( var page = crntPage; page <= this.pages && counter < num + 1; page++, counter++) {
				pagerHtml += '<li id="'+this.tableName +'_pg' + page + '" name="'+this.tableName +'_pg' + page + '">';
				pagerHtml += '<a onclick="'+ this.pagerName + '.showPage(' + page + ');">'+page+'</a></li>';
			}
			
			pagerHtml += '</ul></div>';

			pagerHtml += '<div class="col-md-4 col-xs-4 center"><a class="next '+nextClass+'" onclick="' + this.pagerName + '.next();">Next<i class="fa fa-chevron-right push-left-5"></i></a></div>';

			
			for ( var i = 0; i < this.element.length; i++) {
				var elem = this.element[i];
				elem.innerHTML = pagerHtml;
			}
			
			try {
				var newPageAnchor = document.getElementsByName(this.tableName +'_pg'
						+ this.currentPage);
				for ( var i = 0; i < newPageAnchor.length; i++)
					newPageAnchor[i].className = 'current';
			} catch (err) {
				alert(err);
			}

		} 
		if (this.showSelection) {
			elem.innerHTML = elem.innerHTML + this.selection;
		}
	};
}