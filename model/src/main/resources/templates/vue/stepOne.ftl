<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>vue</title>
<meta name=“renderer” content=“webkit|ie-comp“>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta name="keywords" content="">
<meta name="description" content="">
<link rel="icon" href="${mvcPath}/images/favicon.ico" />
	<!--[if lt IE 9]>
	<script type="text/javascript" src="${mvcPath!""}/webjarslocator/html5shiv/html5shiv.min.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/webjarslocator/respond/respond.min.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/webjarslocator/pie/PIE.js"></script>
	<![endif]-->
<link href="${mvcPath!}/webjarslocator/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${mvcPath!}/layui/css/layui.css" media="all" />
</head>
<body class="childrenBody">
	<div id="app">
	  {{ message }}
	</div>
	<div id="app-2">
	  <span v-bind:title="message">
	    鼠标悬停几秒钟查看此处动态绑定的提示信息！
	  </span>
	</div>
	<div id="app-3">
	  <p v-if="seen">现在你看到我了</p>
	</div>
	<div id="app-4">
	  <ol>
	    <li v-for=" (todo,index) in todos">
	      {{index+1}}.{{ todo.text }}
	    </li>
	  </ol>
	</div>
	<div id="app-5">
	  <p>{{ message }}</p>
	  <button v-on:click="reverseMessage">逆转消息</button>
	</div>
	<div id="app-6">
	  <p><a v-on:click="doSomething">{{ message }}</a></p>
	  <input v-model="message">
	</div>
	<div id="app-7">
	  <ol>
	    <!--
	      现在我们为每个 todo-item 提供 todo 对象
	      todo 对象是变量，即其内容可以是动态的。
	      我们也需要为每个组件提供一个“key”，稍后再
	      作详细解释。
	    -->
	    <todo-item
	      v-for="item in groceryList"
	      v-bind:todo="item"
	      v-bind:key="item.id">
	    </todo-item>
	  </ol>
	</div>
</body>
<script type="text/javascript" src="${mvcPath!""}/layui/layui.js"></script>
<script type="text/javascript" src="${mvcPath!""}/vue/vue.js"></script>
<script type="text/javascript">
	var path = "${mvcPath?js_string}";
	var app = new Vue({
		  el: '#app',
		  data: {
		    message: 'Hello Vue!'
		  }
		});
	var app2 = new Vue({
		  el: '#app-2',
		  data: {
		    message: '页面加载于 ' + new Date().toLocaleString()
		  }
		});
	app2.message = '新消息';
	var app3 = new Vue({
		  el: '#app-3',
		  data: {
		    seen: true
		  }
		});
	//app3.seen = false;
	var app4 = new Vue({
		  el: '#app-4',
		  data: {
		    todos: [
		      { text: '学习 JavaScript' },
		      { text: '学习 Vue' },
		      { text: '整个牛项目' }
		    ]
		  }
		});
	app4.todos.push({ text: '新项目' });
	var app5 = new Vue({
		  el: '#app-5',
		  data: {
		    message: 'Hello Vue.js!'
		  },
		  methods: {
		    reverseMessage: function () {
		      this.message = this.message.split('').reverse().join('')
		    }
		  }
		});
	var app6 = new Vue({
		  el: '#app-6',
		  data: {
		    message: 'Hello Vue!'
		  },
		  methods:{
			doSomething: function (){
				alert(this.message);
			}
		  }
		});
	
	Vue.component('todo-item', {
		  props: ['todo'],
		  template: '<li>{{ todo.text }}</li>'
		})

		var app7 = new Vue({
		  el: '#app-7',
		  data: {
		    groceryList: [
		      { id: 0, text: '蔬菜' },
		      { id: 1, text: '奶酪' },
		      { id: 2, text: '随便其它什么人吃的东西' }
		    ]
		  }
		});
</script>
</html>