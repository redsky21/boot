var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _taggedTemplateLiteral2 = _interopRequireDefault(require("@babel/runtime/helpers/taggedTemplateLiteral"));

var _extends2 = _interopRequireDefault(require("@babel/runtime/helpers/extends"));

var _defineProperty2 = _interopRequireDefault(require("@babel/runtime/helpers/defineProperty"));

var _objectWithoutProperties2 = _interopRequireDefault(require("@babel/runtime/helpers/objectWithoutProperties"));

var _base = _interopRequireDefault(require("@emotion/styled/base"));

var _react = _interopRequireDefault(require("react"));

var _react2 = require("@emotion/react");

var _templateObject, _templateObject2;

var _excluded = ["variant", "delay", "width", "height", "style"];

function ownKeys(object, enumerableOnly) { var keys = Object.keys(object); if (Object.getOwnPropertySymbols) { var symbols = Object.getOwnPropertySymbols(object); enumerableOnly && (symbols = symbols.filter(function (sym) { return Object.getOwnPropertyDescriptor(object, sym).enumerable; })), keys.push.apply(keys, symbols); } return keys; }

function _objectSpread(target) { for (var i = 1; i < arguments.length; i++) { var source = null != arguments[i] ? arguments[i] : {}; i % 2 ? ownKeys(Object(source), !0).forEach(function (key) { (0, _defineProperty2["default"])(target, key, source[key]); }) : Object.getOwnPropertyDescriptors ? Object.defineProperties(target, Object.getOwnPropertyDescriptors(source)) : ownKeys(Object(source)).forEach(function (key) { Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key)); }); } return target; }

var Skeleton = /*#__PURE__*/_react["default"].forwardRef(function (_ref, ref) {
  var _ref$variant = _ref.variant,
      variant = _ref$variant === void 0 ? 'rect' : _ref$variant,
      _ref$delay = _ref.delay,
      delay = _ref$delay === void 0 ? 0 : _ref$delay,
      _ref$width = _ref.width,
      width = _ref$width === void 0 ? '100%' : _ref$width,
      _ref$height = _ref.height,
      height = _ref$height === void 0 ? 15 : _ref$height,
      _ref$style = _ref.style,
      style = _ref$style === void 0 ? {} : _ref$style,
      rest = (0, _objectWithoutProperties2["default"])(_ref, _excluded);
  return /*#__PURE__*/_react["default"].createElement(Element, (0, _extends2["default"])({
    ref: ref,
    variant: variant,
    delay: delay,
    style: _objectSpread(_objectSpread({}, style), {}, {
      width: width,
      height: height
    })
  }, rest));
});

var _default = /*#__PURE__*/_react["default"].memo(Skeleton);

exports["default"] = _default;
var showAndHide = (0, _react2.keyframes)(_templateObject || (_templateObject = (0, _taggedTemplateLiteral2["default"])(["\n  0% {\n    opacity: 1;\n    transform: scale(0.25);\n  }\n\n  5% {\n    opacity: 1;\n    transform: scale(1);\n  }\n\n  70% {\n    opacity: 1\n  }\n\n  75%, 100% {\n    opacity: 0;\n  }\n"])));
var pulse = (0, _react2.keyframes)(_templateObject2 || (_templateObject2 = (0, _taggedTemplateLiteral2["default"])(["\n  0% {\n    opacity: 1;\n  }\n  50% {\n    opacity: 0.6;\n  }\n  100% {\n    opacity: 1;\n  }\n}\n"])));
var Element = (0, _base["default"])("span", process.env.NODE_ENV === "production" ? {
  target: "e1z0pvxf0"
} : {
  target: "e1z0pvxf0",
  label: "Element"
})("display:block;position:relative;", function (_ref2) {
  var _ref2$delay = _ref2.delay,
      delay = _ref2$delay === void 0 ? 0 : _ref2$delay;
  return delay > 0 && /*#__PURE__*/(0, _react2.css)("animation:", showAndHide, " linear 5000ms infinite;animation-delay:", delay, "ms;" + (process.env.NODE_ENV === "production" ? "" : ";label:Element;"), process.env.NODE_ENV === "production" ? "" : "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL1NrZWxldG9uL1NrZWxldG9uLnRzeCJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFpRU8iLCJmaWxlIjoiLi4vLi4vLi4vc3JjL2NvbXBvbmVudHMvU2tlbGV0b24vU2tlbGV0b24udHN4Iiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IFJlYWN0IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IHN0eWxlZCBmcm9tICdAZW1vdGlvbi9zdHlsZWQnO1xyXG5pbXBvcnQgeyBjc3MsIGtleWZyYW1lcyB9IGZyb20gJ0BlbW90aW9uL3JlYWN0JztcclxuXHJcbmV4cG9ydCB0eXBlIFRTa2VsZXRvblByb3BzID0ge1xyXG4gIHZhcmlhbnQ6ICdjaXJjbGUnIHwgJ3JlY3QnO1xyXG4gIHdpZHRoOiBSZWFjdC5DU1NQcm9wZXJ0aWVzWyd3aWR0aCddO1xyXG4gIGhlaWdodDogUmVhY3QuQ1NTUHJvcGVydGllc1snaGVpZ2h0J107XHJcbiAgZGVsYXk/OiBudW1iZXI7XHJcbn0gJiBSZWFjdC5JbnNIVE1MQXR0cmlidXRlczxIVE1MU3BhbkVsZW1lbnQ+O1xyXG5cclxuY29uc3QgU2tlbGV0b24gPSBSZWFjdC5mb3J3YXJkUmVmPEhUTUxTcGFuRWxlbWVudCwgVFNrZWxldG9uUHJvcHM+KFxyXG4gICh7IHZhcmlhbnQgPSAncmVjdCcsIGRlbGF5ID0gMCwgd2lkdGggPSAnMTAwJScsIGhlaWdodCA9IDE1LCBzdHlsZSA9IHt9LCAuLi5yZXN0IH0sIHJlZikgPT4ge1xyXG4gICAgcmV0dXJuIChcclxuICAgICAgPEVsZW1lbnRcclxuICAgICAgICByZWY9e3JlZn1cclxuICAgICAgICB2YXJpYW50PXt2YXJpYW50fVxyXG4gICAgICAgIGRlbGF5PXtkZWxheX1cclxuICAgICAgICBzdHlsZT17eyAuLi5zdHlsZSwgd2lkdGgsIGhlaWdodCB9fVxyXG4gICAgICAgIHsuLi5yZXN0fVxyXG4gICAgICAvPlxyXG4gICAgKTtcclxuICB9LFxyXG4pO1xyXG5cclxuZXhwb3J0IGRlZmF1bHQgUmVhY3QubWVtbyhTa2VsZXRvbik7XHJcblxyXG5jb25zdCBzaG93QW5kSGlkZSA9IGtleWZyYW1lc2BcclxuICAwJSB7XHJcbiAgICBvcGFjaXR5OiAxO1xyXG4gICAgdHJhbnNmb3JtOiBzY2FsZSgwLjI1KTtcclxuICB9XHJcblxyXG4gIDUlIHtcclxuICAgIG9wYWNpdHk6IDE7XHJcbiAgICB0cmFuc2Zvcm06IHNjYWxlKDEpO1xyXG4gIH1cclxuXHJcbiAgNzAlIHtcclxuICAgIG9wYWNpdHk6IDFcclxuICB9XHJcblxyXG4gIDc1JSwgMTAwJSB7XHJcbiAgICBvcGFjaXR5OiAwO1xyXG4gIH1cclxuYDtcclxuXHJcbmNvbnN0IHB1bHNlID0ga2V5ZnJhbWVzYFxyXG4gIDAlIHtcclxuICAgIG9wYWNpdHk6IDE7XHJcbiAgfVxyXG4gIDUwJSB7XHJcbiAgICBvcGFjaXR5OiAwLjY7XHJcbiAgfVxyXG4gIDEwMCUge1xyXG4gICAgb3BhY2l0eTogMTtcclxuICB9XHJcbn1cclxuYDtcclxuXHJcbmNvbnN0IEVsZW1lbnQgPSBzdHlsZWQuc3BhbjxPbWl0PFRTa2VsZXRvblByb3BzLCAnd2lkdGgnIHwgJ2hlaWdodCc+PmBcclxuICBkaXNwbGF5OiBibG9jaztcclxuICBwb3NpdGlvbjogcmVsYXRpdmU7XHJcbiAgJHsoeyBkZWxheSA9IDAgfSkgPT5cclxuICAgIGRlbGF5ID4gMCAmJlxyXG4gICAgY3NzYFxyXG4gICAgICBhbmltYXRpb246ICR7c2hvd0FuZEhpZGV9IGxpbmVhciA1MDAwbXMgaW5maW5pdGU7XHJcbiAgICAgIGFuaW1hdGlvbi1kZWxheTogJHtkZWxheX1tcztcclxuICAgIGB9XHJcblxyXG4gICY6OmFmdGVyIHtcclxuICAgIGNvbnRlbnQ6ICcnO1xyXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xyXG4gICAgdG9wOiAwO1xyXG4gICAgbGVmdDogMDtcclxuICAgIHdpZHRoOiAxMDAlO1xyXG4gICAgaGVpZ2h0OiAxMDAlO1xyXG4gICAgYmFja2dyb3VuZC1jb2xvcjogcmdiYSgxMDAsIDEwMCwgMTAwLCAwLjE1KTtcclxuICAgIGJvcmRlci1yYWRpdXM6ICR7KHsgdmFyaWFudCB9KSA9PiAodmFyaWFudCA9PT0gJ3JlY3QnID8gJzVweCcgOiAnOTk5OXB4Jyl9O1xyXG4gICAgYW5pbWF0aW9uOiAkeygpID0+XHJcbiAgICAgIGNzc2BcclxuICAgICAgICAke3B1bHNlfSAxLjVzIGVhc2UtaW4tb3V0IDAuNXMgaW5maW5pdGU7XHJcbiAgICAgIGB9O1xyXG4gIH1cclxuYDtcclxuIl19 */");
}, " &::after{content:'';position:absolute;top:0;left:0;width:100%;height:100%;background-color:rgba(100, 100, 100, 0.15);border-radius:", function (_ref3) {
  var variant = _ref3.variant;
  return variant === 'rect' ? '5px' : '9999px';
}, ";animation:", function () {
  return /*#__PURE__*/(0, _react2.css)(pulse, " 1.5s ease-in-out 0.5s infinite;" + (process.env.NODE_ENV === "production" ? "" : ";label:Element;"), process.env.NODE_ENV === "production" ? "" : "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL1NrZWxldG9uL1NrZWxldG9uLnRzeCJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFnRlMiLCJmaWxlIjoiLi4vLi4vLi4vc3JjL2NvbXBvbmVudHMvU2tlbGV0b24vU2tlbGV0b24udHN4Iiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IFJlYWN0IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IHN0eWxlZCBmcm9tICdAZW1vdGlvbi9zdHlsZWQnO1xyXG5pbXBvcnQgeyBjc3MsIGtleWZyYW1lcyB9IGZyb20gJ0BlbW90aW9uL3JlYWN0JztcclxuXHJcbmV4cG9ydCB0eXBlIFRTa2VsZXRvblByb3BzID0ge1xyXG4gIHZhcmlhbnQ6ICdjaXJjbGUnIHwgJ3JlY3QnO1xyXG4gIHdpZHRoOiBSZWFjdC5DU1NQcm9wZXJ0aWVzWyd3aWR0aCddO1xyXG4gIGhlaWdodDogUmVhY3QuQ1NTUHJvcGVydGllc1snaGVpZ2h0J107XHJcbiAgZGVsYXk/OiBudW1iZXI7XHJcbn0gJiBSZWFjdC5JbnNIVE1MQXR0cmlidXRlczxIVE1MU3BhbkVsZW1lbnQ+O1xyXG5cclxuY29uc3QgU2tlbGV0b24gPSBSZWFjdC5mb3J3YXJkUmVmPEhUTUxTcGFuRWxlbWVudCwgVFNrZWxldG9uUHJvcHM+KFxyXG4gICh7IHZhcmlhbnQgPSAncmVjdCcsIGRlbGF5ID0gMCwgd2lkdGggPSAnMTAwJScsIGhlaWdodCA9IDE1LCBzdHlsZSA9IHt9LCAuLi5yZXN0IH0sIHJlZikgPT4ge1xyXG4gICAgcmV0dXJuIChcclxuICAgICAgPEVsZW1lbnRcclxuICAgICAgICByZWY9e3JlZn1cclxuICAgICAgICB2YXJpYW50PXt2YXJpYW50fVxyXG4gICAgICAgIGRlbGF5PXtkZWxheX1cclxuICAgICAgICBzdHlsZT17eyAuLi5zdHlsZSwgd2lkdGgsIGhlaWdodCB9fVxyXG4gICAgICAgIHsuLi5yZXN0fVxyXG4gICAgICAvPlxyXG4gICAgKTtcclxuICB9LFxyXG4pO1xyXG5cclxuZXhwb3J0IGRlZmF1bHQgUmVhY3QubWVtbyhTa2VsZXRvbik7XHJcblxyXG5jb25zdCBzaG93QW5kSGlkZSA9IGtleWZyYW1lc2BcclxuICAwJSB7XHJcbiAgICBvcGFjaXR5OiAxO1xyXG4gICAgdHJhbnNmb3JtOiBzY2FsZSgwLjI1KTtcclxuICB9XHJcblxyXG4gIDUlIHtcclxuICAgIG9wYWNpdHk6IDE7XHJcbiAgICB0cmFuc2Zvcm06IHNjYWxlKDEpO1xyXG4gIH1cclxuXHJcbiAgNzAlIHtcclxuICAgIG9wYWNpdHk6IDFcclxuICB9XHJcblxyXG4gIDc1JSwgMTAwJSB7XHJcbiAgICBvcGFjaXR5OiAwO1xyXG4gIH1cclxuYDtcclxuXHJcbmNvbnN0IHB1bHNlID0ga2V5ZnJhbWVzYFxyXG4gIDAlIHtcclxuICAgIG9wYWNpdHk6IDE7XHJcbiAgfVxyXG4gIDUwJSB7XHJcbiAgICBvcGFjaXR5OiAwLjY7XHJcbiAgfVxyXG4gIDEwMCUge1xyXG4gICAgb3BhY2l0eTogMTtcclxuICB9XHJcbn1cclxuYDtcclxuXHJcbmNvbnN0IEVsZW1lbnQgPSBzdHlsZWQuc3BhbjxPbWl0PFRTa2VsZXRvblByb3BzLCAnd2lkdGgnIHwgJ2hlaWdodCc+PmBcclxuICBkaXNwbGF5OiBibG9jaztcclxuICBwb3NpdGlvbjogcmVsYXRpdmU7XHJcbiAgJHsoeyBkZWxheSA9IDAgfSkgPT5cclxuICAgIGRlbGF5ID4gMCAmJlxyXG4gICAgY3NzYFxyXG4gICAgICBhbmltYXRpb246ICR7c2hvd0FuZEhpZGV9IGxpbmVhciA1MDAwbXMgaW5maW5pdGU7XHJcbiAgICAgIGFuaW1hdGlvbi1kZWxheTogJHtkZWxheX1tcztcclxuICAgIGB9XHJcblxyXG4gICY6OmFmdGVyIHtcclxuICAgIGNvbnRlbnQ6ICcnO1xyXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xyXG4gICAgdG9wOiAwO1xyXG4gICAgbGVmdDogMDtcclxuICAgIHdpZHRoOiAxMDAlO1xyXG4gICAgaGVpZ2h0OiAxMDAlO1xyXG4gICAgYmFja2dyb3VuZC1jb2xvcjogcmdiYSgxMDAsIDEwMCwgMTAwLCAwLjE1KTtcclxuICAgIGJvcmRlci1yYWRpdXM6ICR7KHsgdmFyaWFudCB9KSA9PiAodmFyaWFudCA9PT0gJ3JlY3QnID8gJzVweCcgOiAnOTk5OXB4Jyl9O1xyXG4gICAgYW5pbWF0aW9uOiAkeygpID0+XHJcbiAgICAgIGNzc2BcclxuICAgICAgICAke3B1bHNlfSAxLjVzIGVhc2UtaW4tb3V0IDAuNXMgaW5maW5pdGU7XHJcbiAgICAgIGB9O1xyXG4gIH1cclxuYDtcclxuIl19 */");
}, ";}" + (process.env.NODE_ENV === "production" ? "" : "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL1NrZWxldG9uL1NrZWxldG9uLnRzeCJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUE0RHFFIiwiZmlsZSI6Ii4uLy4uLy4uL3NyYy9jb21wb25lbnRzL1NrZWxldG9uL1NrZWxldG9uLnRzeCIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBSZWFjdCBmcm9tICdyZWFjdCc7XHJcbmltcG9ydCBzdHlsZWQgZnJvbSAnQGVtb3Rpb24vc3R5bGVkJztcclxuaW1wb3J0IHsgY3NzLCBrZXlmcmFtZXMgfSBmcm9tICdAZW1vdGlvbi9yZWFjdCc7XHJcblxyXG5leHBvcnQgdHlwZSBUU2tlbGV0b25Qcm9wcyA9IHtcclxuICB2YXJpYW50OiAnY2lyY2xlJyB8ICdyZWN0JztcclxuICB3aWR0aDogUmVhY3QuQ1NTUHJvcGVydGllc1snd2lkdGgnXTtcclxuICBoZWlnaHQ6IFJlYWN0LkNTU1Byb3BlcnRpZXNbJ2hlaWdodCddO1xyXG4gIGRlbGF5PzogbnVtYmVyO1xyXG59ICYgUmVhY3QuSW5zSFRNTEF0dHJpYnV0ZXM8SFRNTFNwYW5FbGVtZW50PjtcclxuXHJcbmNvbnN0IFNrZWxldG9uID0gUmVhY3QuZm9yd2FyZFJlZjxIVE1MU3BhbkVsZW1lbnQsIFRTa2VsZXRvblByb3BzPihcclxuICAoeyB2YXJpYW50ID0gJ3JlY3QnLCBkZWxheSA9IDAsIHdpZHRoID0gJzEwMCUnLCBoZWlnaHQgPSAxNSwgc3R5bGUgPSB7fSwgLi4ucmVzdCB9LCByZWYpID0+IHtcclxuICAgIHJldHVybiAoXHJcbiAgICAgIDxFbGVtZW50XHJcbiAgICAgICAgcmVmPXtyZWZ9XHJcbiAgICAgICAgdmFyaWFudD17dmFyaWFudH1cclxuICAgICAgICBkZWxheT17ZGVsYXl9XHJcbiAgICAgICAgc3R5bGU9e3sgLi4uc3R5bGUsIHdpZHRoLCBoZWlnaHQgfX1cclxuICAgICAgICB7Li4ucmVzdH1cclxuICAgICAgLz5cclxuICAgICk7XHJcbiAgfSxcclxuKTtcclxuXHJcbmV4cG9ydCBkZWZhdWx0IFJlYWN0Lm1lbW8oU2tlbGV0b24pO1xyXG5cclxuY29uc3Qgc2hvd0FuZEhpZGUgPSBrZXlmcmFtZXNgXHJcbiAgMCUge1xyXG4gICAgb3BhY2l0eTogMTtcclxuICAgIHRyYW5zZm9ybTogc2NhbGUoMC4yNSk7XHJcbiAgfVxyXG5cclxuICA1JSB7XHJcbiAgICBvcGFjaXR5OiAxO1xyXG4gICAgdHJhbnNmb3JtOiBzY2FsZSgxKTtcclxuICB9XHJcblxyXG4gIDcwJSB7XHJcbiAgICBvcGFjaXR5OiAxXHJcbiAgfVxyXG5cclxuICA3NSUsIDEwMCUge1xyXG4gICAgb3BhY2l0eTogMDtcclxuICB9XHJcbmA7XHJcblxyXG5jb25zdCBwdWxzZSA9IGtleWZyYW1lc2BcclxuICAwJSB7XHJcbiAgICBvcGFjaXR5OiAxO1xyXG4gIH1cclxuICA1MCUge1xyXG4gICAgb3BhY2l0eTogMC42O1xyXG4gIH1cclxuICAxMDAlIHtcclxuICAgIG9wYWNpdHk6IDE7XHJcbiAgfVxyXG59XHJcbmA7XHJcblxyXG5jb25zdCBFbGVtZW50ID0gc3R5bGVkLnNwYW48T21pdDxUU2tlbGV0b25Qcm9wcywgJ3dpZHRoJyB8ICdoZWlnaHQnPj5gXHJcbiAgZGlzcGxheTogYmxvY2s7XHJcbiAgcG9zaXRpb246IHJlbGF0aXZlO1xyXG4gICR7KHsgZGVsYXkgPSAwIH0pID0+XHJcbiAgICBkZWxheSA+IDAgJiZcclxuICAgIGNzc2BcclxuICAgICAgYW5pbWF0aW9uOiAke3Nob3dBbmRIaWRlfSBsaW5lYXIgNTAwMG1zIGluZmluaXRlO1xyXG4gICAgICBhbmltYXRpb24tZGVsYXk6ICR7ZGVsYXl9bXM7XHJcbiAgICBgfVxyXG5cclxuICAmOjphZnRlciB7XHJcbiAgICBjb250ZW50OiAnJztcclxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcclxuICAgIHRvcDogMDtcclxuICAgIGxlZnQ6IDA7XHJcbiAgICB3aWR0aDogMTAwJTtcclxuICAgIGhlaWdodDogMTAwJTtcclxuICAgIGJhY2tncm91bmQtY29sb3I6IHJnYmEoMTAwLCAxMDAsIDEwMCwgMC4xNSk7XHJcbiAgICBib3JkZXItcmFkaXVzOiAkeyh7IHZhcmlhbnQgfSkgPT4gKHZhcmlhbnQgPT09ICdyZWN0JyA/ICc1cHgnIDogJzk5OTlweCcpfTtcclxuICAgIGFuaW1hdGlvbjogJHsoKSA9PlxyXG4gICAgICBjc3NgXHJcbiAgICAgICAgJHtwdWxzZX0gMS41cyBlYXNlLWluLW91dCAwLjVzIGluZmluaXRlO1xyXG4gICAgICBgfTtcclxuICB9XHJcbmA7XHJcbiJdfQ== */"));