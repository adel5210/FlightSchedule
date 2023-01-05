"use strict";(self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[]).push([[359],{5359:function(t,e,s){s.r(e),s.d(e,{default:function(){return y}});var i=s(2202),r=s(1828),o=s(3058),a=s(5223),n=s(1200),l=s(6904),d=s(5294),u=s(5251),c=function(){var t=this,e=t._self._c;return e("div",[e(n.Z,{staticStyle:{width:"500px","padding-top":"200px"}},[e(i.Z,{attrs:{color:"red",dense:"",dismissible:"",outlined:"",type:"error",cl:""},model:{value:t.isErrorMsg,callback:function(e){t.isErrorMsg=e},expression:"isErrorMsg"}},[t._v(t._s(t.errorMsg)+" ")]),e(i.Z,{attrs:{dense:"",dismissible:"",outlined:"",type:"success",cl:""},model:{value:t.isSuccessMsg,callback:function(e){t.isSuccessMsg=e},expression:"isSuccessMsg"}},[t._v(t._s(t.successMsg)+" ")]),t.isStage1?e(o.Z,{attrs:{elevation:"5",outlined:"",shaped:""}},[e(a.EB,[t._v("Dummy FlightSchedule")]),e(a.Qq,[t._v("Forget password")]),e(a.ZB,[t._v(" Provide an email address to perform a password reset ")]),e(l.Z,{ref:"form",staticStyle:{padding:"30px"},attrs:{"lazy-validation":""}},[e(u.Z,{attrs:{rules:t.emailRules,label:"E-mail",required:""},model:{value:t.email,callback:function(e){t.email=e},expression:"email"}}),e(d.Z,{staticStyle:{"justify-content":"center","align-self":"center"}},[e(r.Z,{attrs:{elevation:"4",outlined:"",raised:"",loading:t.stage1SubmitLoading},on:{click:t.onSubmitStage1}},[t._v(" Submit ")])],1)],1)],1):t._e(),t.isStage2?e(o.Z,{attrs:{elevation:"5",outlined:"",shaped:""}},[e(a.EB,[t._v("Dummy FlightSchedule")]),e(a.Qq,[t._v("Forget password")]),e(a.ZB,[t._v(" Enter one-time-password sent from you email address ")]),e(l.Z,{ref:"form",staticStyle:{padding:"30px"},attrs:{"lazy-validation":""}},[e(d.Z,[e(u.Z,{staticStyle:{"padding-left":"5px","padding-right":"5px","text-align":"center",width:"64px"},attrs:{required:""},on:{keydown:t.limitOtp},model:{value:t.otp,callback:function(e){t.otp=e},expression:"otp"}}),e(r.Z,{attrs:{loading:t.resendOtpLoading,outlined:"",raised:"",small:"",right:""},on:{click:t.onResendOtp}},[t._v("Re-send OTP ")])],1),e(d.Z,{staticStyle:{"justify-content":"center","align-self":"center"}},[e(r.Z,{attrs:{elevation:"4",outlined:"",raised:"",loading:t.stage2SubmitLoading},on:{click:t.onSubmitStage2}},[t._v(" Submit ")])],1)],1)],1):t._e(),t.isStage3?e(o.Z,{attrs:{elevation:"5",outlined:"",shaped:""}},[e(a.EB,[t._v("Dummy FlightSchedule")]),e(a.Qq,[t._v("Forget password")]),e(a.ZB,[t._v(" Enter your new password ")]),e(l.Z,{ref:"form",staticStyle:{padding:"30px"},attrs:{"lazy-validation":""}},[e(d.Z,[e(u.Z,{attrs:{rules:t.passwordRules,label:"New Password",type:"password",required:""},model:{value:t.password,callback:function(e){t.password=e},expression:"password"}})],1),e(d.Z,[e(u.Z,{attrs:{rules:t.passwordRules,label:"Confirm password",type:"password",required:""},model:{value:t.password2,callback:function(e){t.password2=e},expression:"password2"}})],1),e(d.Z,{staticStyle:{"justify-content":"center","align-self":"center"}},[e(r.Z,{attrs:{elevation:"4",outlined:"",raised:"",loading:t.stage3SubmitLoading,disabled:t.submittedNewPassword},on:{click:t.onSubmitStage3}},[t._v(" Submit ")])],1)],1)],1):t._e()],1)],1)},h=[],p=(s(7658),s(6354)),g=s(7238),m={name:"ForgetPasswordComponent",data(){return{isStage1:!0,isStage2:!1,isStage3:!1,errorMsg:"",isErrorMsg:!1,email:"",otp:"",emailRules:[t=>!!t||"E-mail is required",t=>/.+@.+/.test(t)||"E-mail must be valid"],stage1SubmitLoading:!1,stage2SubmitLoading:!1,stage3SubmitLoading:!1,resendOtpLoading:!1,password:"",password2:"",passwordRules:[t=>!!t||"Password is required",t=>t.length>=8||"Min 8 characters",t=>t===this.password||"Mismatch password"],isSuccessMsg:!1,successMsg:"",submittedNewPassword:!1}},methods:{onSubmitStage1(){if(!this.$refs.form.validate())return;this.stage1SubmitLoading=!0,this.isErrorMsg=!1;let t=new p.Z;t.email=this.email,g.Z.resendOtp(t).then((t=>{console.log(t),this.isStage1=!1,this.isStage2=!0,this.$refs.form.resetValidation()})).catch((t=>{console.error(t),this.errorMsg=t.response.data.message,this.isErrorMsg=!0})).finally((()=>{this.stage1SubmitLoading=!1}))},onResendOtp(){this.resendOtpLoading=!0,this.isErrorMsg=!1;let t=new p.Z;t.email=this.email,g.Z.resendOtp(t).then((t=>{console.log(t),this.$refs.form.resetValidation()})).catch((t=>{console.error(t),this.errorMsg=t.response.data.message,this.isErrorMsg=!0})).finally((()=>{this.resendOtpLoading=!1}))},onSubmitStage2(){if(!this.$refs.form.validate())return;this.isErrorMsg=!1,this.stage2SubmitLoading=!0;let t=new p.Z;t.email=this.email,t.otp=this.otp,g.Z.validateOtp(t).then((t=>{console.log(t),this.isStage2=!1,this.isStage3=!0,this.$refs.form.resetValidation()})).catch((t=>{console.error(t),this.errorMsg=t.response.data.message,this.isErrorMsg=!0})).finally((()=>{this.stage2SubmitLoading=!1}))},onSubmitStage3(){if(!this.$refs.form.validate())return;this.isErrorMsg=!1,this.stage3SubmitLoading=!0;let t=new p.Z;t.email=this.email,t.newPassword=this.password,g.Z.resetPassword(t).then((t=>{console.log(t),this.successMsg="Successfully change password",this.isSuccessMsg=!0,this.submittedNewPassword=!0,setTimeout((()=>this.$router.push("login")),5e3)})).catch((t=>{console.error(t),this.errorMsg=t.response.data.message,this.isErrorMsg=!0})).finally((()=>{this.stage3SubmitLoading=!1}))},limitOtp(){this.otp=/^\d+$/.test(this.otp)?this.otp:"",this.otp=this.otp.length>5?this.otp.substring(0,5):this.otp}},computed:{loggedIn(){return this.$store.state.status.loggedIn}},created(){this.loggedIn&&this.$router.replace("dashboard")}},f=m,v=s(1001),b=(0,v.Z)(f,c,h,!1,null,"5247ad59",null),y=b.exports},2202:function(t,e,s){s.d(e,{Z:function(){return h}});var i=s(2925),r=s(1903),o=s(1012),a=s(6470),n=s(3276),l=s(144),d=l.ZP.extend({name:"transitionable",props:{mode:String,origin:String,transition:String}}),u=s(4987),c=s(6596),h=(0,u.Z)(i.Z,a.Z,d).extend({name:"v-alert",props:{border:{type:String,validator(t){return["top","right","bottom","left"].includes(t)}},closeLabel:{type:String,default:"$vuetify.close"},coloredBorder:Boolean,dense:Boolean,dismissible:Boolean,closeIcon:{type:String,default:"$cancel"},icon:{default:"",type:[Boolean,String],validator(t){return"string"===typeof t||!1===t}},outlined:Boolean,prominent:Boolean,text:Boolean,type:{type:String,validator(t){return["info","error","success","warning"].includes(t)}},value:{type:Boolean,default:!0}},computed:{__cachedBorder(){if(!this.border)return null;let t={staticClass:"v-alert__border",class:{[`v-alert__border--${this.border}`]:!0}};return this.coloredBorder&&(t=this.setBackgroundColor(this.computedColor,t),t.class["v-alert__border--has-color"]=!0),this.$createElement("div",t)},__cachedDismissible(){if(!this.dismissible)return null;const t=this.iconColor;return this.$createElement(r.Z,{staticClass:"v-alert__dismissible",props:{color:t,icon:!0,small:!0},attrs:{"aria-label":this.$vuetify.lang.t(this.closeLabel)},on:{click:()=>this.isActive=!1}},[this.$createElement(o.Z,{props:{color:t}},this.closeIcon)])},__cachedIcon(){return this.computedIcon?this.$createElement(o.Z,{staticClass:"v-alert__icon",props:{color:this.iconColor}},this.computedIcon):null},classes(){const t={...i.Z.options.computed.classes.call(this),"v-alert--border":Boolean(this.border),"v-alert--dense":this.dense,"v-alert--outlined":this.outlined,"v-alert--prominent":this.prominent,"v-alert--text":this.text};return this.border&&(t[`v-alert--border-${this.border}`]=!0),t},computedColor(){return this.color||this.type},computedIcon(){return!1!==this.icon&&("string"===typeof this.icon&&this.icon?this.icon:!!["error","info","success","warning"].includes(this.type)&&`$${this.type}`)},hasColoredIcon(){return this.hasText||Boolean(this.border)&&this.coloredBorder},hasText(){return this.text||this.outlined},iconColor(){return this.hasColoredIcon?this.computedColor:void 0},isDark(){return!(!this.type||this.coloredBorder||this.outlined)||n.Z.options.computed.isDark.call(this)}},created(){this.$attrs.hasOwnProperty("outline")&&(0,c.fK)("outline","outlined",this)},methods:{genWrapper(){const t=[this.$slots.prepend||this.__cachedIcon,this.genContent(),this.__cachedBorder,this.$slots.append,this.$scopedSlots.close?this.$scopedSlots.close({toggle:this.toggle}):this.__cachedDismissible],e={staticClass:"v-alert__wrapper"};return this.$createElement("div",e,t)},genContent(){return this.$createElement("div",{staticClass:"v-alert__content"},this.$slots.default)},genAlert(){let t={staticClass:"v-alert",attrs:{role:"alert"},on:this.listeners$,class:this.classes,style:this.styles,directives:[{name:"show",value:this.isActive}]};if(!this.coloredBorder){const e=this.hasText?this.setTextColor:this.setBackgroundColor;t=e(this.computedColor,t)}return this.$createElement("div",t,[this.genWrapper()])},toggle(){this.isActive=!this.isActive}},render(t){const e=this.genAlert();return this.transition?t("transition",{props:{name:this.transition,origin:this.origin,mode:this.mode}},[e]):e}})},6904:function(t,e,s){s(7658);var i=s(4987),r=s(4609),o=s(380);e["Z"]=(0,i.Z)(r.Z,(0,o.J)("form")).extend({name:"v-form",provide(){return{form:this}},inheritAttrs:!1,props:{disabled:Boolean,lazyValidation:Boolean,readonly:Boolean,value:Boolean},data:()=>({inputs:[],watchers:[],errorBag:{}}),watch:{errorBag:{handler(t){const e=Object.values(t).includes(!0);this.$emit("input",!e)},deep:!0,immediate:!0}},methods:{watchInput(t){const e=t=>t.$watch("hasError",(e=>{this.$set(this.errorBag,t._uid,e)}),{immediate:!0}),s={_uid:t._uid,valid:()=>{},shouldValidate:()=>{}};return this.lazyValidation?s.shouldValidate=t.$watch("shouldValidate",(i=>{i&&(this.errorBag.hasOwnProperty(t._uid)||(s.valid=e(t)))})):s.valid=e(t),s},validate(){return 0===this.inputs.filter((t=>!t.validate(!0))).length},reset(){this.inputs.forEach((t=>t.reset())),this.resetErrorBag()},resetErrorBag(){this.lazyValidation&&setTimeout((()=>{this.errorBag={}}),0)},resetValidation(){this.inputs.forEach((t=>t.resetValidation())),this.resetErrorBag()},register(t){this.inputs.push(t),this.watchers.push(this.watchInput(t))},unregister(t){const e=this.inputs.find((e=>e._uid===t._uid));if(!e)return;const s=this.watchers.find((t=>t._uid===e._uid));s&&(s.valid(),s.shouldValidate()),this.watchers=this.watchers.filter((t=>t._uid!==e._uid)),this.inputs=this.inputs.filter((t=>t._uid!==e._uid)),this.$delete(this.errorBag,e._uid)}},render(t){return t("form",{staticClass:"v-form",attrs:{novalidate:!0,...this.attrs$},on:{submit:t=>this.$emit("submit",t)}},this.$slots.default)}})},5294:function(t,e,s){s(7658),s(1884);var i=s(144),r=s(2653),o=s(1050);const a=["sm","md","lg","xl"],n=["start","end","center"];function l(t,e){return a.reduce(((s,i)=>(s[t+(0,o.jC)(i)]=e(),s)),{})}const d=t=>[...n,"baseline","stretch"].includes(t),u=l("align",(()=>({type:String,default:null,validator:d}))),c=t=>[...n,"space-between","space-around"].includes(t),h=l("justify",(()=>({type:String,default:null,validator:c}))),p=t=>[...n,"space-between","space-around","stretch"].includes(t),g=l("alignContent",(()=>({type:String,default:null,validator:p}))),m={align:Object.keys(u),justify:Object.keys(h),alignContent:Object.keys(g)},f={align:"align",justify:"justify",alignContent:"align-content"};function v(t,e,s){let i=f[t];if(null!=s){if(e){const s=e.replace(t,"");i+=`-${s}`}return i+=`-${s}`,i.toLowerCase()}}const b=new Map;e["Z"]=i.ZP.extend({name:"v-row",functional:!0,props:{tag:{type:String,default:"div"},dense:Boolean,noGutters:Boolean,align:{type:String,default:null,validator:d},...u,justify:{type:String,default:null,validator:c},...h,alignContent:{type:String,default:null,validator:p},...g},render(t,{props:e,data:s,children:i}){let o="";for(const r in e)o+=String(e[r]);let a=b.get(o);if(!a){let t;for(t in a=[],m)m[t].forEach((s=>{const i=e[s],r=v(t,s,i);r&&a.push(r)}));a.push({"no-gutters":e.noGutters,"row--dense":e.dense,[`align-${e.align}`]:e.align,[`justify-${e.justify}`]:e.justify,[`align-content-${e.alignContent}`]:e.alignContent}),b.set(o,a)}return t(e.tag,(0,r.ZP)(s,{staticClass:"row",class:a}),i)}})},6354:function(t,e,s){function i(t){return i="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(t){return typeof t}:function(t){return t&&"function"==typeof Symbol&&t.constructor===Symbol&&t!==Symbol.prototype?"symbol":typeof t},i(t)}function r(t,e){if("object"!==i(t)||null===t)return t;var s=t[Symbol.toPrimitive];if(void 0!==s){var r=s.call(t,e||"default");if("object"!==i(r))return r;throw new TypeError("@@toPrimitive must return a primitive value.")}return("string"===e?String:Number)(t)}function o(t){var e=r(t,"string");return"symbol"===i(e)?e:String(e)}function a(t,e,s){return e=o(e),e in t?Object.defineProperty(t,e,{value:s,enumerable:!0,configurable:!0,writable:!0}):t[e]=s,t}s.d(e,{Z:function(){return n}});class n{constructor(){a(this,"email",void 0),a(this,"password",void 0),a(this,"firstName",void 0),a(this,"lastName",void 0),a(this,"otp",void 0),a(this,"username",void 0),a(this,"token",void 0),a(this,"newPassword",void 0)}}}}]);
//# sourceMappingURL=359.9684e9b5.js.map