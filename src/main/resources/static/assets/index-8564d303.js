import{r as S,ad as ut,c as Y,a as l,ae as M,af as se,f as Re,ag as ht,ah as vt,d as de,u as ft,g as Ue,ai as mt,b as pt,h as T,t as gt,e as bt,aj as Ce,Z as pe,ak as wt,i as ze,j as xt,k as ge,l as z,V as yt,m as kt,al as $t,o as _t,T as St,am as le,an as ie,z as Te,a0 as Rt,A as Ct,K as zt,S as Ne,B as J,C as be,J as o,R as f,U as c,D as i,N as re,X as g,a8 as U,a9 as N,Q as Tt,_ as L,ao as Vt,I as It,a5 as Mt,ap as Dt,L as K,aq as Bt,ar as Ut,F as Ve,as as Nt,at as Ie,W as At,au as Et,av as Ft}from"./index-5609e1c2.js";function Me(n){return window.TouchEvent&&n instanceof window.TouchEvent}function De(){const n=S(new Map),s=k=>x=>{n.value.set(k,x)};return ut(()=>n.value.clear()),[n,s]}const Ht=Y([l("slider",`
 display: block;
 padding: calc((var(--n-handle-size) - var(--n-rail-height)) / 2) 0;
 position: relative;
 z-index: 0;
 width: 100%;
 cursor: pointer;
 user-select: none;
 -webkit-user-select: none;
 `,[M("reverse",[l("slider-handles",[l("slider-handle-wrapper",`
 transform: translate(50%, -50%);
 `)]),l("slider-dots",[l("slider-dot",`
 transform: translateX(50%, -50%);
 `)]),M("vertical",[l("slider-handles",[l("slider-handle-wrapper",`
 transform: translate(-50%, -50%);
 `)]),l("slider-marks",[l("slider-mark",`
 transform: translateY(calc(-50% + var(--n-dot-height) / 2));
 `)]),l("slider-dots",[l("slider-dot",`
 transform: translateX(-50%) translateY(0);
 `)])])]),M("vertical",`
 padding: 0 calc((var(--n-handle-size) - var(--n-rail-height)) / 2);
 width: var(--n-rail-width-vertical);
 height: 100%;
 `,[l("slider-handles",`
 top: calc(var(--n-handle-size) / 2);
 right: 0;
 bottom: calc(var(--n-handle-size) / 2);
 left: 0;
 `,[l("slider-handle-wrapper",`
 top: unset;
 left: 50%;
 transform: translate(-50%, 50%);
 `)]),l("slider-rail",`
 height: 100%;
 `,[se("fill",`
 top: unset;
 right: 0;
 bottom: unset;
 left: 0;
 `)]),M("with-mark",`
 width: var(--n-rail-width-vertical);
 margin: 0 32px 0 8px;
 `),l("slider-marks",`
 top: calc(var(--n-handle-size) / 2);
 right: unset;
 bottom: calc(var(--n-handle-size) / 2);
 left: 22px;
 font-size: var(--n-mark-font-size);
 `,[l("slider-mark",`
 transform: translateY(50%);
 white-space: nowrap;
 `)]),l("slider-dots",`
 top: calc(var(--n-handle-size) / 2);
 right: unset;
 bottom: calc(var(--n-handle-size) / 2);
 left: 50%;
 `,[l("slider-dot",`
 transform: translateX(-50%) translateY(50%);
 `)])]),M("disabled",`
 cursor: not-allowed;
 opacity: var(--n-opacity-disabled);
 `,[l("slider-handle",`
 cursor: not-allowed;
 `)]),M("with-mark",`
 width: 100%;
 margin: 8px 0 32px 0;
 `),Y("&:hover",[l("slider-rail",{backgroundColor:"var(--n-rail-color-hover)"},[se("fill",{backgroundColor:"var(--n-fill-color-hover)"})]),l("slider-handle",{boxShadow:"var(--n-handle-box-shadow-hover)"})]),M("active",[l("slider-rail",{backgroundColor:"var(--n-rail-color-hover)"},[se("fill",{backgroundColor:"var(--n-fill-color-hover)"})]),l("slider-handle",{boxShadow:"var(--n-handle-box-shadow-hover)"})]),l("slider-marks",`
 position: absolute;
 top: 18px;
 left: calc(var(--n-handle-size) / 2);
 right: calc(var(--n-handle-size) / 2);
 `,[l("slider-mark",`
 position: absolute;
 transform: translateX(-50%);
 white-space: nowrap;
 `)]),l("slider-rail",`
 width: 100%;
 position: relative;
 height: var(--n-rail-height);
 background-color: var(--n-rail-color);
 transition: background-color .3s var(--n-bezier);
 border-radius: calc(var(--n-rail-height) / 2);
 `,[se("fill",`
 position: absolute;
 top: 0;
 bottom: 0;
 border-radius: calc(var(--n-rail-height) / 2);
 transition: background-color .3s var(--n-bezier);
 background-color: var(--n-fill-color);
 `)]),l("slider-handles",`
 position: absolute;
 top: 0;
 right: calc(var(--n-handle-size) / 2);
 bottom: 0;
 left: calc(var(--n-handle-size) / 2);
 `,[l("slider-handle-wrapper",`
 outline: none;
 position: absolute;
 top: 50%;
 transform: translate(-50%, -50%);
 cursor: pointer;
 display: flex;
 `,[l("slider-handle",`
 height: var(--n-handle-size);
 width: var(--n-handle-size);
 border-radius: 50%;
 overflow: hidden;
 transition: box-shadow .2s var(--n-bezier), background-color .3s var(--n-bezier);
 background-color: var(--n-handle-color);
 box-shadow: var(--n-handle-box-shadow);
 `,[Y("&:hover",`
 box-shadow: var(--n-handle-box-shadow-hover);
 `)]),Y("&:focus",[l("slider-handle",`
 box-shadow: var(--n-handle-box-shadow-focus);
 `,[Y("&:hover",`
 box-shadow: var(--n-handle-box-shadow-active);
 `)])])])]),l("slider-dots",`
 position: absolute;
 top: 50%;
 left: calc(var(--n-handle-size) / 2);
 right: calc(var(--n-handle-size) / 2);
 `,[M("transition-disabled",[l("slider-dot","transition: none;")]),l("slider-dot",`
 transition:
 border-color .3s var(--n-bezier),
 box-shadow .3s var(--n-bezier),
 background-color .3s var(--n-bezier);
 position: absolute;
 transform: translate(-50%, -50%);
 height: var(--n-dot-height);
 width: var(--n-dot-width);
 border-radius: var(--n-dot-border-radius);
 overflow: hidden;
 box-sizing: border-box;
 border: var(--n-dot-border);
 background-color: var(--n-dot-color);
 `,[M("active","border: var(--n-dot-border-active);")])])]),l("slider-handle-indicator",`
 font-size: var(--n-font-size);
 padding: 6px 10px;
 border-radius: var(--n-indicator-border-radius);
 color: var(--n-indicator-text-color);
 background-color: var(--n-indicator-color);
 box-shadow: var(--n-indicator-box-shadow);
 `,[Re()]),l("slider-handle-indicator",`
 font-size: var(--n-font-size);
 padding: 6px 10px;
 border-radius: var(--n-indicator-border-radius);
 color: var(--n-indicator-text-color);
 background-color: var(--n-indicator-color);
 box-shadow: var(--n-indicator-box-shadow);
 `,[M("top",`
 margin-bottom: 12px;
 `),M("right",`
 margin-left: 12px;
 `),M("bottom",`
 margin-top: 12px;
 `),M("left",`
 margin-right: 12px;
 `),Re()]),ht(l("slider",[l("slider-dot","background-color: var(--n-dot-color-modal);")])),vt(l("slider",[l("slider-dot","background-color: var(--n-dot-color-popover);")]))]),Pt=0,jt=Object.assign(Object.assign({},Ue.props),{to:ge.propTo,defaultValue:{type:[Number,Array],default:0},marks:Object,disabled:{type:Boolean,default:void 0},formatTooltip:Function,keyboard:{type:Boolean,default:!0},min:{type:Number,default:0},max:{type:Number,default:100},step:{type:[Number,String],default:1},range:Boolean,value:[Number,Array],placement:String,showTooltip:{type:Boolean,default:void 0},tooltip:{type:Boolean,default:!0},vertical:Boolean,reverse:Boolean,"onUpdate:value":[Function,Array],onUpdateValue:[Function,Array]}),Be=de({name:"Slider",props:jt,setup(n){const{mergedClsPrefixRef:s,namespaceRef:k,inlineThemeDisabled:x}=ft(n),h=Ue("Slider","-slider",Ht,mt,n,s),m=S(null),[$,_]=De(),[w,b]=De(),D=S(new Set),W=pt(n),{mergedDisabledRef:H}=W,P=T(()=>{const{step:e}=n;if(e<=0||e==="mark")return 0;const t=e.toString();let a=0;return t.includes(".")&&(a=t.length-t.indexOf(".")-1),a}),G=S(n.defaultValue),ce=gt(n,"value"),X=bt(ce,G),V=T(()=>{const{value:e}=X;return(n.range?e:[e]).map(ke)}),q=T(()=>V.value.length>2),u=T(()=>n.placement===void 0?n.vertical?"right":"top":n.placement),p=T(()=>{const{marks:e}=n;return e?Object.keys(e).map(parseFloat):null}),d=S(-1),E=S(-1),B=S(-1),I=S(!1),Q=S(!1),ue=T(()=>{const{vertical:e,reverse:t}=n;return e?t?"top":"bottom":t?"right":"left"}),Ae=T(()=>{if(q.value)return;const e=V.value,t=Z(n.range?Math.min(...e):n.min),a=Z(n.range?Math.max(...e):e[0]),{value:r}=ue;return n.vertical?{[r]:`${t}%`,height:`${a-t}%`}:{[r]:`${t}%`,width:`${a-t}%`}}),Ee=T(()=>{const e=[],{marks:t}=n;if(t){const a=V.value.slice();a.sort((R,C)=>R-C);const{value:r}=ue,{value:v}=q,{range:y}=n,A=v?()=>!1:R=>y?R>=a[0]&&R<=a[a.length-1]:R<=a[0];for(const R of Object.keys(t)){const C=Number(R);e.push({active:A(C),label:t[R],style:{[r]:`${Z(C)}%`}})}}return e});function Fe(e,t){const a=Z(e),{value:r}=ue;return{[r]:`${a}%`,zIndex:t===d.value?1:0}}function we(e){return n.showTooltip||B.value===e||d.value===e&&I.value}function He(e){return I.value?!(d.value===e&&E.value===e):!0}function Pe(e){var t;~e&&(d.value=e,(t=$.value.get(e))===null||t===void 0||t.focus())}function je(){w.value.forEach((e,t)=>{we(t)&&e.syncPosition()})}function xe(e){const{"onUpdate:value":t,onUpdateValue:a}=n,{nTriggerFormInput:r,nTriggerFormChange:v}=W;a&&Te(a,e),t&&Te(t,e),G.value=e,r(),v()}function ye(e){const{range:t}=n;if(t){if(Array.isArray(e)){const{value:a}=V;e.join()!==a.join()&&xe(e)}}else Array.isArray(e)||V.value[0]!==e&&xe(e)}function he(e,t){if(n.range){const a=V.value.slice();a.splice(t,1,e),ye(a)}else ye(e)}function ve(e,t,a){const r=a!==void 0;a||(a=e-t>0?1:-1);const v=p.value||[],{step:y}=n;if(y==="mark"){const C=ee(e,v.concat(t),r?a:void 0);return C?C.value:t}if(y<=0)return t;const{value:A}=P;let R;if(r){const C=Number((t/y).toFixed(A)),F=Math.floor(C),fe=C>F?F:F-1,me=C<F?F:F+1;R=ee(t,[Number((fe*y).toFixed(A)),Number((me*y).toFixed(A)),...v],a)}else{const C=Le(e);R=ee(e,[...v,C])}return R?ke(R.value):t}function ke(e){return Math.min(n.max,Math.max(n.min,e))}function Z(e){const{max:t,min:a}=n;return(e-a)/(t-a)*100}function Oe(e){const{max:t,min:a}=n;return a+(t-a)*e}function Le(e){const{step:t,min:a}=n;if(t<=0||t==="mark")return e;const r=Math.round((e-a)/t)*t+a;return Number(r.toFixed(P.value))}function ee(e,t=p.value,a){if(!(t!=null&&t.length))return null;let r=null,v=-1;for(;++v<t.length;){const y=t[v]-e,A=Math.abs(y);(a===void 0||y*a>0)&&(r===null||A<r.distance)&&(r={index:v,distance:A,value:t[v]})}return r}function $e(e){const t=m.value;if(!t)return;const a=Me(e)?e.touches[0]:e,r=t.getBoundingClientRect();let v;return n.vertical?v=(r.bottom-a.clientY)/r.height:v=(a.clientX-r.left)/r.width,n.reverse&&(v=1-v),Oe(v)}function Ke(e){if(H.value||!n.keyboard)return;const{vertical:t,reverse:a}=n;switch(e.key){case"ArrowUp":e.preventDefault(),te(t&&a?-1:1);break;case"ArrowRight":e.preventDefault(),te(!t&&a?-1:1);break;case"ArrowDown":e.preventDefault(),te(t&&a?1:-1);break;case"ArrowLeft":e.preventDefault(),te(!t&&a?1:-1);break}}function te(e){const t=d.value;if(t===-1)return;const{step:a}=n,r=V.value[t],v=a<=0||a==="mark"?r:r+a*e;he(ve(v,r,e>0?1:-1),t)}function Ge(e){var t,a;if(H.value||!Me(e)&&e.button!==Pt)return;const r=$e(e);if(r===void 0)return;const v=V.value.slice(),y=n.range?(a=(t=ee(r,v))===null||t===void 0?void 0:t.index)!==null&&a!==void 0?a:-1:0;y!==-1&&(e.preventDefault(),Pe(y),Xe(),he(ve(r,V.value[y]),y))}function Xe(){I.value||(I.value=!0,le("touchend",document,oe),le("mouseup",document,oe),le("touchmove",document,ne),le("mousemove",document,ne))}function ae(){I.value&&(I.value=!1,ie("touchend",document,oe),ie("mouseup",document,oe),ie("touchmove",document,ne),ie("mousemove",document,ne))}function ne(e){const{value:t}=d;if(!I.value||t===-1){ae();return}const a=$e(e);he(ve(a,V.value[t]),t)}function oe(){ae()}function Ye(e){d.value=e,H.value||(B.value=e)}function Je(e){d.value===e&&(d.value=-1,ae()),B.value===e&&(B.value=-1)}function We(e){B.value=e}function qe(e){B.value===e&&(B.value=-1)}Ce(d,(e,t)=>void pe(()=>E.value=t)),Ce(X,()=>{if(n.marks){if(Q.value)return;Q.value=!0,pe(()=>{Q.value=!1})}pe(je)}),wt(()=>{ae()});const _e=T(()=>{const{self:{markFontSize:e,railColor:t,railColorHover:a,fillColor:r,fillColorHover:v,handleColor:y,opacityDisabled:A,dotColor:R,dotColorModal:C,handleBoxShadow:F,handleBoxShadowHover:fe,handleBoxShadowActive:me,handleBoxShadowFocus:Qe,dotBorder:Ze,dotBoxShadow:et,railHeight:tt,railWidthVertical:at,handleSize:nt,dotHeight:ot,dotWidth:st,dotBorderRadius:lt,fontSize:it,dotBorderActive:rt,dotColorPopover:dt},common:{cubicBezierEaseInOut:ct}}=h.value;return{"--n-bezier":ct,"--n-dot-border":Ze,"--n-dot-border-active":rt,"--n-dot-border-radius":lt,"--n-dot-box-shadow":et,"--n-dot-color":R,"--n-dot-color-modal":C,"--n-dot-color-popover":dt,"--n-dot-height":ot,"--n-dot-width":st,"--n-fill-color":r,"--n-fill-color-hover":v,"--n-font-size":it,"--n-handle-box-shadow":F,"--n-handle-box-shadow-active":me,"--n-handle-box-shadow-focus":Qe,"--n-handle-box-shadow-hover":fe,"--n-handle-color":y,"--n-handle-size":nt,"--n-opacity-disabled":A,"--n-rail-color":t,"--n-rail-color-hover":a,"--n-rail-height":tt,"--n-rail-width-vertical":at,"--n-mark-font-size":e}}),j=x?ze("slider",void 0,_e,n):void 0,Se=T(()=>{const{self:{fontSize:e,indicatorColor:t,indicatorBoxShadow:a,indicatorTextColor:r,indicatorBorderRadius:v}}=h.value;return{"--n-font-size":e,"--n-indicator-border-radius":v,"--n-indicator-box-shadow":a,"--n-indicator-color":t,"--n-indicator-text-color":r}}),O=x?ze("slider-indicator",void 0,Se,n):void 0;return{mergedClsPrefix:s,namespace:k,uncontrolledValue:G,mergedValue:X,mergedDisabled:H,mergedPlacement:u,isMounted:xt(),adjustedTo:ge(n),dotTransitionDisabled:Q,markInfos:Ee,isShowTooltip:we,shouldKeepTooltipTransition:He,handleRailRef:m,setHandleRefs:_,setFollowerRefs:b,fillStyle:Ae,getHandleStyle:Fe,activeIndex:d,arrifiedValues:V,followerEnabledIndexSet:D,handleRailMouseDown:Ge,handleHandleFocus:Ye,handleHandleBlur:Je,handleHandleMouseEnter:We,handleHandleMouseLeave:qe,handleRailKeyDown:Ke,indicatorCssVars:x?void 0:Se,indicatorThemeClass:O==null?void 0:O.themeClass,indicatorOnRender:O==null?void 0:O.onRender,cssVars:x?void 0:_e,themeClass:j==null?void 0:j.themeClass,onRender:j==null?void 0:j.onRender}},render(){var n;const{mergedClsPrefix:s,themeClass:k,formatTooltip:x}=this;return(n=this.onRender)===null||n===void 0||n.call(this),z("div",{class:[`${s}-slider`,k,{[`${s}-slider--disabled`]:this.mergedDisabled,[`${s}-slider--active`]:this.activeIndex!==-1,[`${s}-slider--with-mark`]:this.marks,[`${s}-slider--vertical`]:this.vertical,[`${s}-slider--reverse`]:this.reverse}],style:this.cssVars,onKeydown:this.handleRailKeyDown,onMousedown:this.handleRailMouseDown,onTouchstart:this.handleRailMouseDown},z("div",{class:`${s}-slider-rail`},z("div",{class:`${s}-slider-rail__fill`,style:this.fillStyle}),this.marks?z("div",{class:[`${s}-slider-dots`,this.dotTransitionDisabled&&`${s}-slider-dots--transition-disabled`]},this.markInfos.map(h=>z("div",{key:h.label,class:[`${s}-slider-dot`,{[`${s}-slider-dot--active`]:h.active}],style:h.style}))):null,z("div",{ref:"handleRailRef",class:`${s}-slider-handles`},this.arrifiedValues.map((h,m)=>{const $=this.isShowTooltip(m);return z(yt,null,{default:()=>[z(kt,null,{default:()=>z("div",{ref:this.setHandleRefs(m),class:`${s}-slider-handle-wrapper`,tabindex:this.mergedDisabled?-1:0,style:this.getHandleStyle(h,m),onFocus:()=>this.handleHandleFocus(m),onBlur:()=>this.handleHandleBlur(m),onMouseenter:()=>this.handleHandleMouseEnter(m),onMouseleave:()=>this.handleHandleMouseLeave(m)},$t(this.$slots.thumb,()=>[z("div",{class:`${s}-slider-handle`})]))}),this.tooltip&&z(_t,{ref:this.setFollowerRefs(m),show:$,to:this.adjustedTo,enabled:this.showTooltip&&!this.range||this.followerEnabledIndexSet.has(m),teleportDisabled:this.adjustedTo===ge.tdkey,placement:this.mergedPlacement,containerClass:this.namespace},{default:()=>z(St,{name:"fade-in-scale-up-transition",appear:this.isMounted,css:this.shouldKeepTooltipTransition(m),onEnter:()=>{this.followerEnabledIndexSet.add(m)},onAfterLeave:()=>{this.followerEnabledIndexSet.delete(m)}},{default:()=>{var _;return $?((_=this.indicatorOnRender)===null||_===void 0||_.call(this),z("div",{class:[`${s}-slider-handle-indicator`,this.indicatorThemeClass,`${s}-slider-handle-indicator--${this.mergedPlacement}`],style:this.indicatorCssVars},typeof x=="function"?x(h):h)):null}})})]})})),this.marks?z("div",{class:`${s}-slider-marks`},this.markInfos.map(h=>z("div",{key:h.label,class:`${s}-slider-mark`,style:h.style},h.label))):null))}});function Ot(){const n=new Date,s=n.getDate(),k=n.getMonth()+1;return`${n.getFullYear()}-${k}-${s}`}const Lt={class:"p-4 space-y-5 min-h-[200px]"},Kt={class:"space-y-6"},Gt={class:"flex items-center space-x-4"},Xt={class:"flex-shrink-0 w-[100px]"},Yt={class:"flex-1"},Jt={class:"flex items-center space-x-4"},Wt={class:"flex-shrink-0 w-[100px]"},qt={class:"w-[200px]"},Qt={class:"flex items-center space-x-4"},Zt={class:"flex-shrink-0 w-[100px]"},ea={class:"flex-1"},ta={class:"flex-shrink-0 w-[100px]"},aa={class:"flex flex-wrap items-center gap-4"},na={class:"flex items-center space-x-4"},oa={class:"flex-shrink-0 w-[100px]"},sa={class:"flex flex-wrap items-center gap-4"},la={class:"flex items-center space-x-4"},ia={class:"flex-shrink-0 w-[100px]"},ra={class:"flex flex-wrap items-center gap-4"},da={class:"flex items-center space-x-4"},ca={class:"flex-shrink-0 w-[100px]"},ua=de({__name:"General",setup(n){const s=Rt(),k=Ct(),{isMobile:x}=zt(),h=Ne(),m=T(()=>s.theme),$=T(()=>k.userInfo),_=S($.value.avatar??""),w=S($.value.name??""),b=S($.value.description??""),D=T({get(){return s.language},set(u){s.setLanguage(u)}}),W=[{label:"Auto",key:"auto",icon:"ri:contrast-line"},{label:"Light",key:"light",icon:"ri:sun-foggy-line"},{label:"Dark",key:"dark",icon:"ri:moon-foggy-line"}],H=[{label:"English",key:"en-US",value:"en-US"},{label:"Español",key:"es-ES",value:"es-ES"},{label:"한국어",key:"ko-KR",value:"ko-KR"},{label:"Русский язык",key:"ru-RU",value:"ru-RU"},{label:"Tiếng Việt",key:"vi-VN",value:"vi-VN"},{label:"简体中文",key:"zh-CN",value:"zh-CN"},{label:"繁體中文",key:"zh-TW",value:"zh-TW"}];function P(u){k.updateUserInfo(u),h.success(K("common.success"))}function G(){k.resetUserInfo(),h.success(K("common.success")),window.location.reload()}function ce(){const u=Ot(),p=localStorage.getItem("chatStorage")||"{}",d=JSON.stringify(JSON.parse(p),null,2),E=new Blob([d],{type:"application/json"}),B=URL.createObjectURL(E),I=document.createElement("a");I.href=B,I.download=`chat-store_${u}.json`,document.body.appendChild(I),I.click(),document.body.removeChild(I)}function X(u){const p=u.target;if(!p||!p.files)return;const d=p.files[0];if(!d)return;const E=new FileReader;E.onload=()=>{try{const B=JSON.parse(E.result);localStorage.setItem("chatStorage",JSON.stringify(B)),h.success(K("common.success")),location.reload()}catch{h.error(K("common.invalidFileFormat"))}},E.readAsText(d)}function V(){localStorage.removeItem("chatStorage"),location.reload()}function q(){const u=document.getElementById("fileInput");u&&u.click()}return(u,p)=>(J(),be("div",Lt,[o("div",Kt,[o("div",Gt,[o("span",Xt,f(u.$t("setting.avatarLink")),1),o("div",Yt,[c(i(re),{value:_.value,"onUpdate:value":p[0]||(p[0]=d=>_.value=d),placeholder:""},null,8,["value"])]),c(i(N),{size:"tiny",text:"",type:"primary",onClick:p[1]||(p[1]=d=>P({avatar:_.value}))},{default:g(()=>[U(f(u.$t("common.save")),1)]),_:1})]),o("div",Jt,[o("span",Wt,f(u.$t("setting.name")),1),o("div",qt,[c(i(re),{value:w.value,"onUpdate:value":p[2]||(p[2]=d=>w.value=d),placeholder:""},null,8,["value"])]),c(i(N),{size:"tiny",text:"",type:"primary",onClick:p[3]||(p[3]=d=>P({name:w.value}))},{default:g(()=>[U(f(u.$t("common.save")),1)]),_:1})]),o("div",Qt,[o("span",Zt,f(u.$t("setting.description")),1),o("div",ea,[c(i(re),{value:b.value,"onUpdate:value":p[4]||(p[4]=d=>b.value=d),placeholder:""},null,8,["value"])]),c(i(N),{size:"tiny",text:"",type:"primary",onClick:p[5]||(p[5]=d=>P({description:b.value}))},{default:g(()=>[U(f(u.$t("common.save")),1)]),_:1})]),o("div",{class:Tt(["flex items-center space-x-4",i(x)&&"items-start"])},[o("span",ta,f(u.$t("setting.chatHistory")),1),o("div",aa,[c(i(N),{size:"small",onClick:ce},{icon:g(()=>[c(i(L),{icon:"ri:download-2-fill"})]),default:g(()=>[U(" "+f(u.$t("common.export")),1)]),_:1}),o("input",{id:"fileInput",type:"file",style:{display:"none"},onChange:X},null,32),c(i(N),{size:"small",onClick:q},{icon:g(()=>[c(i(L),{icon:"ri:upload-2-fill"})]),default:g(()=>[U(" "+f(u.$t("common.import")),1)]),_:1}),c(i(Vt),{placement:"bottom",onPositiveClick:V},{trigger:g(()=>[c(i(N),{size:"small"},{icon:g(()=>[c(i(L),{icon:"ri:close-circle-line"})]),default:g(()=>[U(" "+f(u.$t("common.clear")),1)]),_:1})]),default:g(()=>[U(" "+f(u.$t("chat.clearHistoryConfirm")),1)]),_:1})])],2),o("div",na,[o("span",oa,f(u.$t("setting.theme")),1),o("div",sa,[(J(),be(It,null,Mt(W,d=>c(i(N),{key:d.key,size:"small",type:d.key===i(m)?"primary":void 0,onClick:E=>i(s).setTheme(d.key)},{icon:g(()=>[c(i(L),{icon:d.icon},null,8,["icon"])]),_:2},1032,["type","onClick"])),64))])]),o("div",la,[o("span",ia,f(u.$t("setting.language")),1),o("div",ra,[c(i(Dt),{style:{width:"140px"},value:i(D),options:H,onUpdateValue:p[6]||(p[6]=d=>i(s).setLanguage(d))},null,8,["value"])])]),o("div",da,[o("span",ca,f(u.$t("setting.resetUserInfo")),1),c(i(N),{size:"small",onClick:G},{default:g(()=>[U(f(u.$t("common.reset")),1)]),_:1})])])]))}}),ha={class:"p-4 space-y-5 min-h-[200px]"},va={class:"space-y-6"},fa={class:"flex items-center space-x-4"},ma={class:"flex-shrink-0 w-[120px]"},pa={class:"flex-1"},ga={class:"flex items-center space-x-4"},ba={class:"flex-shrink-0 w-[120px]"},wa={class:"flex-1"},xa={class:"flex items-center space-x-4"},ya={class:"flex-shrink-0 w-[120px]"},ka={class:"flex-1"},$a={class:"flex items-center space-x-4"},_a=o("span",{class:"flex-shrink-0 w-[120px]"}," ",-1),Sa=de({__name:"Advanced",setup(n){const s=Bt(),k=Ne(),x=S(s.systemMessage??""),h=S(s.temperature??.5),m=S(s.top_p??1);function $(w){s.updateSetting(w),k.success(K("common.success"))}function _(){s.resetSetting(),k.success(K("common.success")),window.location.reload()}return(w,b)=>(J(),be("div",ha,[o("div",va,[o("div",fa,[o("span",ma,f(w.$t("setting.role")),1),o("div",pa,[c(i(re),{value:x.value,"onUpdate:value":b[0]||(b[0]=D=>x.value=D),type:"textarea",autosize:{minRows:1,maxRows:4}},null,8,["value"])]),c(i(N),{size:"tiny",text:"",type:"primary",onClick:b[1]||(b[1]=D=>$({systemMessage:x.value}))},{default:g(()=>[U(f(w.$t("common.save")),1)]),_:1})]),o("div",ga,[o("span",ba,f(w.$t("setting.temperature")),1),o("div",wa,[c(i(Be),{value:h.value,"onUpdate:value":b[2]||(b[2]=D=>h.value=D),max:2,min:0,step:.1},null,8,["value","step"])]),o("span",null,f(h.value),1),c(i(N),{size:"tiny",text:"",type:"primary",onClick:b[3]||(b[3]=D=>$({temperature:h.value}))},{default:g(()=>[U(f(w.$t("common.save")),1)]),_:1})]),o("div",xa,[o("span",ya,f(w.$t("setting.top_p")),1),o("div",ka,[c(i(Be),{value:m.value,"onUpdate:value":b[4]||(b[4]=D=>m.value=D),max:1,min:0,step:.1},null,8,["value","step"])]),o("span",null,f(m.value),1),c(i(N),{size:"tiny",text:"",type:"primary",onClick:b[5]||(b[5]=D=>$({top_p:m.value}))},{default:g(()=>[U(f(w.$t("common.save")),1)]),_:1})]),o("div",$a,[_a,c(i(N),{size:"small",onClick:_},{default:g(()=>[U(f(w.$t("common.reset")),1)]),_:1})])])]))}}),Ra={class:"ml-2"},Ca={class:"min-h-[100px]"},za={class:"ml-2"},Ta={class:"min-h-[100px]"},Ia=de({__name:"index",props:{visible:{type:Boolean}},emits:["update:visible"],setup(n,{emit:s}){const k=n,x=Ut(),h=T(()=>!!x.isChatGPTAPI),m=S("General"),$=T({get(){return k.visible},set(_){s("update:visible",_)}});return(_,w)=>(J(),Ve(i(Ft),{show:i($),"onUpdate:show":w[1]||(w[1]=b=>Et($)?$.value=b:null),"auto-focus":!1,preset:"card",style:{width:"95%","max-width":"640px"}},{default:g(()=>[o("div",null,[c(i(Nt),{value:m.value,"onUpdate:value":w[0]||(w[0]=b=>m.value=b),type:"line",animated:""},{default:g(()=>[c(i(Ie),{name:"General",tab:"General"},{tab:g(()=>[c(i(L),{class:"text-lg",icon:"ri:file-user-line"}),o("span",Ra,f(_.$t("setting.general")),1)]),default:g(()=>[o("div",Ca,[c(ua)])]),_:1}),i(h)?(J(),Ve(i(Ie),{key:0,name:"Advanced",tab:"Advanced"},{tab:g(()=>[c(i(L),{class:"text-lg",icon:"ri:equalizer-line"}),o("span",za,f(_.$t("setting.advanced")),1)]),default:g(()=>[o("div",Ta,[c(Sa)])]),_:1})):At("",!0)]),_:1},8,["value"])])]),_:1},8,["show"]))}});export{Ia as default};
