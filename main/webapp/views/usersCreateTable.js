 


window.onload = function() {
  var Table = createMatrix();
	'use strict';

	function Field(field) {
		this.fieldSide	= 330,
		this.shipSide	= 33,
		this.shipsData	= [
			'',
			[4, 'fourdeck'],
			[3, 'tripledeck'],
			[2, 'doubledeck'],
			[1, 'singledeck']
		],
		this.field		= field;
		this.fieldX		= field.getBoundingClientRect().top + window.pageYOffset;
		this.fieldY		= field.getBoundingClientRect().left + window.pageXOffset;
		this.fieldRight	= this.fieldY + this.fieldSide;
		this.fieldBtm	= this.fieldX + this.fieldSide;
		this.squadron	= [];
		this.startGame	= false;
	}

	Field.prototype.randomLocationShips = function() {
		this.matrix = createMatrix();

		for (var i = 1, length = this.shipsData.length; i < length; i++) {
			var decks = this.shipsData[i][0]; // кол-во палуб
			for (var j = 0; j < i; j++) {
				var fc = this.getCoordinatesDecks(decks);
				fc.decks 	= decks,
				fc.shipname	= this.shipsData[i][1] + String(j + 1);
				var ship = new Ships(this, fc);
					ship.createShip();
			}
		}
	}

	Field.prototype.getCoordinatesDecks = function(decks) {
		var kx = getRandom(1),
			ky = (kx == 0) ? 1 : 0,
			x, y;
		if (kx == 0) {
			x = getRandom(9);
			y = getRandom(10 - decks);
		} else {
			x = getRandom(10 - decks);
			y = getRandom(9);
		}
		var result = this.checkLocationShip(x, y, kx, ky, decks);
		if (!result) return this.getCoordinatesDecks(decks);
		var obj = {
			x: x,
			y: y,
			kx: kx,
			ky: ky
		};
		return obj;
	}

	Field.prototype.checkLocationShip = function(x, y, kx, ky, decks) {
		var fromX, toX, fromY, toY;
		fromX = (x == 0) ? x : x - 1;
		if (x + kx * decks == 10 && kx == 1) toX = x + kx * decks;
		else if (x + kx * decks < 10 && kx == 1) toX = x + kx * decks + 1;
		else if (x == 9 && kx == 0) toX = x + 1;
		else if (x < 9 && kx == 0) toX = x + 2;

		fromY = (y == 0) ? y : y - 1;
		if (y + ky * decks == 10 && ky == 1) toY = y + ky * decks;
		else if (y + ky * decks < 10 && ky == 1) toY = y + ky * decks + 1;
		else if (y == 9 && ky == 0) toY = y + 1;
		else if (y < 9 && ky == 0) toY = y + 2;

		if (toX === undefined || toY === undefined) return false;

		for (var i = fromX; i < toX; i++) {
			for (var j = fromY; j < toY; j++) {
				if (this.matrix[i][j] == 1) return false;
			}
		}
		return true;
	}

	Field.prototype.cleanField = function() {
		var parent	= this.field,
			id		= parent.getAttribute('id'),
			divs 	= document.querySelectorAll('#' + id + ' > div');
		[].forEach.call(divs, function(el) {
			parent.removeChild(el);
		});
		this.squadron.length = 0;
	}
	var userfield = getElement('field_user');
	var user = new Field(getElement('field_user'));

	/////////////////////////////////////////

	function Ships(player, fc) {
		this.player 	= player;
		this.shipname 	= fc.shipname;
		//количество палуб
		this.decks		= fc.decks;
		this.x0			= fc.x;
		this.y0			= fc.y;
		this.kx			= fc.kx;
		this.ky 		= fc.ky;
		this.hits 		= 0;
		this.matrix		= [];
	}

	Ships.prototype.createShip = function() {
		var k		= 0,
			x		= this.x0,
			y		= this.y0,
			kx		= this.kx,
			ky		= this.ky,
			decks	= this.decks,
			player	= this.player

		while (k < decks) {
			player.matrix[x + k * kx][y + k * ky] = 1;
			this.matrix.push([x + k * kx, y + k * ky]);
			k++;
		}
		player.squadron.push(this);
		if (player == user) this.showShip();
		if (user.squadron.length == 10) {
			getElement('play').setAttribute('data-hidden', 'false');
		}
    Table = player.matrix;
	}

	Ships.prototype.showShip = function() {
		var div			= document.createElement('div'),
			dir			= (this.kx == 1) ? ' vertical' : '',
			classname	= this.shipname.slice(0, -1),
			player		= this.player;

		div.setAttribute('id', this.shipname);
		div.className = 'ship ' + classname + dir;
		div.style.cssText = 'left:' + (this.y0 * player.shipSide) + 'px; top:' + (this.x0 * player.shipSide) + 'px;';
		player.field.appendChild(div);
	}


	/////////////////////////////////////////

	function Instance() {
		this.pressed = false;
	}

	Instance.prototype.setObserver = function() {
		var fieldUser		= getElement('field_user'),
			initialShips	= getElement('ships_collection');

		fieldUser.addEventListener('mousedown', this.onMouseDown.bind(this));
		fieldUser.addEventListener('contextmenu', this.rotationShip.bind(this));
		initialShips.addEventListener('mousedown', this.onMouseDown.bind(this));
		document.addEventListener('mousemove', this.onMouseMove.bind(this));
		document.addEventListener('mouseup', this.onMouseUp.bind(this));
	}

	Instance.prototype.onMouseDown = function(e) {
		if (e.which != 1 || userfield.startGame) return;
		var el = e.target.closest('.ship');
		if (!el) return;
		this.pressed = true;

		this.draggable = {
			elem:	el,
			downX:	e.pageX,
			downY:	e.pageY,
			kx:		0,
			ky:		1
		};

		if (el.parentElement.getAttribute('id') == 'field_user') {
			var name = el.getAttribute('id');
			this.getDirectionShip(name);

			var computedStyle	= getComputedStyle(el);
			this.draggable.left	= computedStyle.left.slice(0, -2);
			this.draggable.top	= computedStyle.top.slice(0, -2);

			this.cleanShip(el);
		}
		return false;
	}

	Instance.prototype.onMouseMove = function(e) {
		if (this.pressed == false || !this.draggable.elem) return;

		var coords;

		if (!this.clone) {
			this.clone = this.creatClone(e);
			if (!this.clone) return;

			// получаем координаты клона
			coords = getCoords(this.clone);
			this.shiftX = this.draggable.downX - coords.left;
			this.shiftY = this.draggable.downY - coords.top;
			document.body.appendChild(this.clone);
			this.clone.style.zIndex = '1000';
			this.decks = this.getCountDecks();
		}

		var currLeft	= e.pageX - this.shiftX,
			currTop		= e.pageY - this.shiftY;

		this.clone.style.left = currLeft + 'px';
		this.clone.style.top = currTop + 'px';

		coords = getCoords(this.clone);

		var currBtm		= coords.bottom,
			currRight	= coords.right;

		if (currLeft >= user.fieldY - 14 && currRight <= user.fieldRight + 14 && currTop >= user.fieldX - 14 && currBtm <= user.fieldBtm + 14) {
			var	coords = this.getCoordsClone(this.decks);
			var result = user.checkLocationShip(coords.x, coords.y, this.draggable.kx, this.draggable.ky, this.decks);

			if (result) {
				this.clone.classList.remove('unsuccess');
				this.clone.classList.add('success');
			} else {
				this.clone.classList.remove('success');
				this.clone.classList.add('unsuccess');
			}
		} else {
			this.clone.classList.remove('success');
			this.clone.classList.add('unsuccess');
		}
		return false;
	}

	Instance.prototype.onMouseUp = function(e) {
		this.pressed = false;
		if (!this.clone) return;

		if (this.clone.classList.contains('unsuccess')) {
			this.clone.classList.remove('unsuccess');
			this.clone.rollback();

			if (this.draggable.left !== undefined && this.draggable.top !== undefined) {
				this.draggable.elem.style.cssText = 'left:' + this.draggable.left + 'px; top:' + this.draggable.top + 'px;';
			}
		} else {
			var	coords = this.getCoordsClone(this.decks);
			user.field.appendChild(this.clone);
			this.clone.style.left = coords.left + 'px';
			this.clone.style.top = coords.top + 'px';

			var	fc = {
					'shipname': this.clone.getAttribute('id'),
					'x': coords.x,
					'y': coords.y,
					'kx': this.draggable.kx,
					'ky': this.draggable.ky,
					'decks': this.decks
				},
				ship = new Ships(user, fc);

			ship.createShip();
			getElement(ship.shipname).style.zIndex = null;
			getElement('field_user').removeChild(this.clone);
		}

		this.cleanClone();
		return false;
	}

	Instance.prototype.creatClone = function(e) {
			// создаём клон корабля
		var clone	= this.draggable.elem,
			old		= {
				parent:			clone.parentNode,
				nextSibling:	clone.nextSibling,
				left:			clone.left || '',
				top:			clone.top || '',
				zIndex:			clone.zIndex || ''
			};

		clone.rollback = function() {
			old.parent.insertBefore(clone, old.nextSibling);
			clone.style.left = old.left;
			clone.style.top = old.top;
			clone.style.zIndex = old.zIndex;
		};
		return clone;
	}

	Instance.prototype.findDroppable = function(e) {
		this.clone.hidden = true;
		var el = document.elementFromPoint(e.clientX, e.clientY);
		this.clone.hidden = false;
		return el.closest('.ships');
	}

	Instance.prototype.getCountDecks = function() {
		var type = this.clone.getAttribute('id').slice(0, -1);
		for (var i = 1, length = user.shipsData.length; i < length; i++) {
			if (user.shipsData[i][1] === type) {
				return user.shipsData[i][0];
			}
		}
	}

	Instance.prototype.getCoordsClone = function(decks) {
		var pos		= this.clone.getBoundingClientRect(),
			left	= pos.left - user.fieldY,
			right	= pos.right - user.fieldY,
			top		= pos.top - user.fieldX,
			bottom	= pos.bottom - user.fieldX,
			coords	= {};

		coords.top	= (top < 0) ? 0 : (bottom > user.fieldSide) ? user.fieldSide - user.shipSide : top;
		coords.top	= Math.round(coords.top / user.shipSide) * user.shipSide;
		coords.x	= coords.top / user.shipSide;

		coords.left = (left < 0) ? 0 : (right > user.fieldSide) ? user.fieldSide - user.shipSide * decks : left;
		coords.left = Math.round(coords.left / user.shipSide) * user.shipSide;
		coords.y	= coords.left / user.shipSide;
		return coords;
	}

	Instance.prototype.cleanClone = function() {
		delete this.clone;
		delete this.draggable;
	}

	Instance.prototype.rotationShip = function(e) {
		if (e.which != 3 || userfield.startGame) {
			e.preventDefault();
			return;
		}
		e.preventDefault();
		e.stopPropagation();

		var id = e.target.getAttribute('id');

		for (var i = 0, length = user.squadron.length; i < length; i++) {
			var data = user.squadron[i];
			if (data.shipname == id && data.decks != 1) {
				var kx	= (data.kx == 0) ? 1 : 0,
					ky	= (data.ky == 0) ? 1 : 0;

				this.cleanShip(e.target);
				user.field.removeChild(e.target);

				var result = user.checkLocationShip(data.x0, data.y0, kx, ky, data.decks);
				if (result === false) {
					var kx	= (kx == 0) ? 1 : 0,
						ky	= (ky == 0) ? 1 : 0;
				}

				var	fc = {
						'shipname': data.shipname,
						'x': data.x0,
						'y': data.y0,
						'kx': kx,
						'ky': ky,
						'decks': data.decks
					},
					ship = new Ships(user, fc);

				ship.createShip();

				if (result === false) {
					var el = getElement(ship.shipname);
					el.classList.add('unsuccess');
					setTimeout(function() {
						el.classList.remove('unsuccess');
					}, 500);
				}
			}
		}
		return false;
	}


	Instance.prototype.cleanShip = function(el) {
		var coords = el.getBoundingClientRect(),
			x = Math.round((coords.top - user.fieldX) / user.shipSide),
			y = Math.round((coords.left - user.fieldY) / user.shipSide),
			data, k;

		for (var i = 0, length = user.squadron.length; i < length; i++) {
			data = user.squadron[i];
			if (data.x0 == x && data.y0 == y) {
				k = 0;
				while(k < data.decks) {
					user.matrix[x + k * data.kx][y + k * data.ky] = 0;
					k++;
				}
				user.squadron.splice(i, 1);
				return;
			}
		}
	}

	Instance.prototype.getDirectionShip = function(shipname) {
		var data;
		for (var i = 0, length = user.squadron.length; i < length; i++) {
			data = user.squadron[i];
			if (data.shipname === shipname) {
				this.draggable.kx = data.kx;
				this.draggable.ky = data.ky;
				return;
			}
		}
	}

	/////////////////////////////////////////

	getElement('type_placement').addEventListener('click', function(e) {
		var el = e.target;
		if (el.tagName != 'SPAN') return;

		var shipsCollection = getElement('ships_collection');
		getElement('play').setAttribute('data-hidden', true);
		user.cleanField();

		var type = el.getAttribute('data-target'),
			typeGeneration = {
				'random': function() {
					shipsCollection.setAttribute('data-hidden', true);
					user.randomLocationShips();
				},
				'manually': function() {
					user.matrix = createMatrix();
					if (shipsCollection.getAttribute('data-hidden') === 'true') {
						shipsCollection.setAttribute('data-hidden', false);
						var instance = new Instance();
						instance.setObserver();
					} else {
						shipsCollection.setAttribute('data-hidden', true);
					}
				}
			};
		typeGeneration[type]();
	});

	getElement('play').addEventListener('click', function(e) {
        var xml = new XMLHttpRequest();
        xml.open('POST','/BattleServlet',true);
        xml.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                        xml.onreadystatechange = function() {
                        if (xml.readyState != 4) {
                            return
                        }
                        if (xml.status == 200) {
                            var response = xml.responseText;
                            window.location.reload();
                        } else {
                            alert("Error");
                        }
                    }

        var message = "User_matrix=";
        alert("The ships are set up");
        for (let i = 0; i < 10; i++) {
          for (let j = 0; j < 10; j++) {
            message+=Table[i][j];
          }
        }
        xml.send(message);
	});


	/////////////////////////////////////////

	function getElement(id) {
		return document.getElementById(id);
	}

	function getRandom(n) {
		return Math.floor(Math.random() * (n + 1));
	}

	function createMatrix() {
		var x = 10, y = 10, arr = [10];
		for (var i = 0; i < x; i++) {
			arr[i] = [10];
			for(var j = 0; j < y; j++) {
				arr[i][j] = 0;
			}
		}
		return arr;
	}

	function getCoords(el) {
		var coords = el.getBoundingClientRect();
		return {
			left:	coords.left + window.pageXOffset,
			right:	coords.right + window.pageXOffset,
			top:	coords.top + window.pageYOffset,
			bottom: coords.bottom + window.pageYOffset
		};
	}
}