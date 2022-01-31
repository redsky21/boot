var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = OverlaySpinner;

var _extends2 = _interopRequireDefault(require("@babel/runtime/helpers/extends"));

var _objectWithoutProperties2 = _interopRequireDefault(require("@babel/runtime/helpers/objectWithoutProperties"));

var _base = _interopRequireDefault(require("@emotion/styled/base"));

var _react = _interopRequireDefault(require("react"));

var _CircularProgress = _interopRequireDefault(require("@mui/material/CircularProgress"));

var _excluded = ["backgroundColor", "message", "WrapperProps"];

function _EMOTION_STRINGIFIED_CSS_ERROR__() { return "You have tried to stringify object returned from `css` function. It isn't supposed to be used directly (e.g. as value of the `className` prop), but rather handed to emotion so it can handle it (e.g. as value of `css` prop)."; }

function OverlaySpinner(_ref) {
  var _ref$backgroundColor = _ref.backgroundColor,
      backgroundColor = _ref$backgroundColor === void 0 ? 'inherit' : _ref$backgroundColor,
      message = _ref.message,
      WrapperProps = _ref.WrapperProps,
      rest = (0, _objectWithoutProperties2["default"])(_ref, _excluded);
  return /*#__PURE__*/_react["default"].createElement(Wrapper, (0, _extends2["default"])({}, WrapperProps, {
    backgroundColor: backgroundColor
  }), /*#__PURE__*/_react["default"].createElement(_CircularProgress["default"], (0, _extends2["default"])({
    disableShrink: true,
    size: "1em",
    color: "inherit"
  }, rest)), message ? /*#__PURE__*/_react["default"].createElement(Message, null, message) : null);
}

var Wrapper = (0, _base["default"])("div", process.env.NODE_ENV === "production" ? {
  target: "epcsvhp1"
} : {
  target: "epcsvhp1",
  label: "Wrapper"
})("position:absolute;top:0;bottom:0;left:0;right:0;display:flex;flex-direction:column;gap:16px;align-items:center;justify-content:center;z-index:1800;background-color:", function (_ref2) {
  var backgroundColor = _ref2.backgroundColor;
  return backgroundColor;
}, ";" + (process.env.NODE_ENV === "production" ? "" : "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL092ZXJsYXlTcGlubmVyL092ZXJsYXlTcGlubmVyLnRzeCJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUF3QnVGIiwiZmlsZSI6Ii4uLy4uLy4uL3NyYy9jb21wb25lbnRzL092ZXJsYXlTcGlubmVyL092ZXJsYXlTcGlubmVyLnRzeCIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBSZWFjdCBmcm9tICdyZWFjdCc7XHJcbmltcG9ydCBzdHlsZWQgZnJvbSAnQGVtb3Rpb24vc3R5bGVkJztcclxuaW1wb3J0IENpcmN1bGFyUHJvZ3Jlc3MsIHsgQ2lyY3VsYXJQcm9ncmVzc1Byb3BzIH0gZnJvbSAnQG11aS9tYXRlcmlhbC9DaXJjdWxhclByb2dyZXNzJztcclxuXHJcbnR5cGUgVE92ZXJsYXlTcGlubmVyUHJvcHMgPSB7XHJcbiAgYmFja2dyb3VuZENvbG9yPzogUmVhY3QuQ1NTUHJvcGVydGllc1snYmFja2dyb3VuZENvbG9yJ107XHJcbiAgbWVzc2FnZT86IHN0cmluZztcclxuICBXcmFwcGVyUHJvcHM/OiBSZWNvcmQ8c3RyaW5nLCBhbnk+O1xyXG59ICYgQ2lyY3VsYXJQcm9ncmVzc1Byb3BzO1xyXG5cclxuZXhwb3J0IGRlZmF1bHQgZnVuY3Rpb24gT3ZlcmxheVNwaW5uZXIoe1xyXG4gIGJhY2tncm91bmRDb2xvciA9ICdpbmhlcml0JyxcclxuICBtZXNzYWdlLFxyXG4gIFdyYXBwZXJQcm9wcyxcclxuICAuLi5yZXN0XHJcbn06IFRPdmVybGF5U3Bpbm5lclByb3BzKSB7XHJcbiAgcmV0dXJuIChcclxuICAgIDxXcmFwcGVyIHsuLi5XcmFwcGVyUHJvcHN9IGJhY2tncm91bmRDb2xvcj17YmFja2dyb3VuZENvbG9yfT5cclxuICAgICAgPENpcmN1bGFyUHJvZ3Jlc3MgZGlzYWJsZVNocmluaz17dHJ1ZX0gc2l6ZT1cIjFlbVwiIGNvbG9yPVwiaW5oZXJpdFwiIHsuLi5yZXN0fSAvPlxyXG4gICAgICB7bWVzc2FnZSA/IDxNZXNzYWdlPnttZXNzYWdlfTwvTWVzc2FnZT4gOiBudWxsfVxyXG4gICAgPC9XcmFwcGVyPlxyXG4gICk7XHJcbn1cclxuXHJcbmNvbnN0IFdyYXBwZXIgPSBzdHlsZWQuZGl2PHsgYmFja2dyb3VuZENvbG9yOiBSZWFjdC5DU1NQcm9wZXJ0aWVzWydiYWNrZ3JvdW5kQ29sb3InXSB9PmBcclxuICBwb3NpdGlvbjogYWJzb2x1dGU7XHJcbiAgdG9wOiAwO1xyXG4gIGJvdHRvbTogMDtcclxuICBsZWZ0OiAwO1xyXG4gIHJpZ2h0OiAwO1xyXG4gIGRpc3BsYXk6IGZsZXg7XHJcbiAgZmxleC1kaXJlY3Rpb246IGNvbHVtbjtcclxuICBnYXA6IDE2cHg7XHJcbiAgYWxpZ24taXRlbXM6IGNlbnRlcjtcclxuICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcclxuICB6LWluZGV4OiAxODAwO1xyXG4gIGJhY2tncm91bmQtY29sb3I6ICR7KHsgYmFja2dyb3VuZENvbG9yIH0pID0+IGJhY2tncm91bmRDb2xvcn07XHJcbmA7XHJcblxyXG5jb25zdCBNZXNzYWdlID0gc3R5bGVkLmRpdmBcclxuICBmb250LXNpemU6IDE0cHg7XHJcbiAgbGluZS1oZWlnaHQ6IDE0cHg7XHJcbiAgY29sb3I6IHJnYmEoMCwgMCwgMCwgMC43KTtcclxuYDtcclxuIl19 */"));
var Message = (0, _base["default"])("div", process.env.NODE_ENV === "production" ? {
  target: "epcsvhp0"
} : {
  target: "epcsvhp0",
  label: "Message"
})(process.env.NODE_ENV === "production" ? {
  name: "6dpla4",
  styles: "font-size:14px;line-height:14px;color:rgba(0, 0, 0, 0.7)"
} : {
  name: "6dpla4",
  styles: "font-size:14px;line-height:14px;color:rgba(0, 0, 0, 0.7)",
  map: "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL092ZXJsYXlTcGlubmVyL092ZXJsYXlTcGlubmVyLnRzeCJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUF1QzBCIiwiZmlsZSI6Ii4uLy4uLy4uL3NyYy9jb21wb25lbnRzL092ZXJsYXlTcGlubmVyL092ZXJsYXlTcGlubmVyLnRzeCIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBSZWFjdCBmcm9tICdyZWFjdCc7XHJcbmltcG9ydCBzdHlsZWQgZnJvbSAnQGVtb3Rpb24vc3R5bGVkJztcclxuaW1wb3J0IENpcmN1bGFyUHJvZ3Jlc3MsIHsgQ2lyY3VsYXJQcm9ncmVzc1Byb3BzIH0gZnJvbSAnQG11aS9tYXRlcmlhbC9DaXJjdWxhclByb2dyZXNzJztcclxuXHJcbnR5cGUgVE92ZXJsYXlTcGlubmVyUHJvcHMgPSB7XHJcbiAgYmFja2dyb3VuZENvbG9yPzogUmVhY3QuQ1NTUHJvcGVydGllc1snYmFja2dyb3VuZENvbG9yJ107XHJcbiAgbWVzc2FnZT86IHN0cmluZztcclxuICBXcmFwcGVyUHJvcHM/OiBSZWNvcmQ8c3RyaW5nLCBhbnk+O1xyXG59ICYgQ2lyY3VsYXJQcm9ncmVzc1Byb3BzO1xyXG5cclxuZXhwb3J0IGRlZmF1bHQgZnVuY3Rpb24gT3ZlcmxheVNwaW5uZXIoe1xyXG4gIGJhY2tncm91bmRDb2xvciA9ICdpbmhlcml0JyxcclxuICBtZXNzYWdlLFxyXG4gIFdyYXBwZXJQcm9wcyxcclxuICAuLi5yZXN0XHJcbn06IFRPdmVybGF5U3Bpbm5lclByb3BzKSB7XHJcbiAgcmV0dXJuIChcclxuICAgIDxXcmFwcGVyIHsuLi5XcmFwcGVyUHJvcHN9IGJhY2tncm91bmRDb2xvcj17YmFja2dyb3VuZENvbG9yfT5cclxuICAgICAgPENpcmN1bGFyUHJvZ3Jlc3MgZGlzYWJsZVNocmluaz17dHJ1ZX0gc2l6ZT1cIjFlbVwiIGNvbG9yPVwiaW5oZXJpdFwiIHsuLi5yZXN0fSAvPlxyXG4gICAgICB7bWVzc2FnZSA/IDxNZXNzYWdlPnttZXNzYWdlfTwvTWVzc2FnZT4gOiBudWxsfVxyXG4gICAgPC9XcmFwcGVyPlxyXG4gICk7XHJcbn1cclxuXHJcbmNvbnN0IFdyYXBwZXIgPSBzdHlsZWQuZGl2PHsgYmFja2dyb3VuZENvbG9yOiBSZWFjdC5DU1NQcm9wZXJ0aWVzWydiYWNrZ3JvdW5kQ29sb3InXSB9PmBcclxuICBwb3NpdGlvbjogYWJzb2x1dGU7XHJcbiAgdG9wOiAwO1xyXG4gIGJvdHRvbTogMDtcclxuICBsZWZ0OiAwO1xyXG4gIHJpZ2h0OiAwO1xyXG4gIGRpc3BsYXk6IGZsZXg7XHJcbiAgZmxleC1kaXJlY3Rpb246IGNvbHVtbjtcclxuICBnYXA6IDE2cHg7XHJcbiAgYWxpZ24taXRlbXM6IGNlbnRlcjtcclxuICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcclxuICB6LWluZGV4OiAxODAwO1xyXG4gIGJhY2tncm91bmQtY29sb3I6ICR7KHsgYmFja2dyb3VuZENvbG9yIH0pID0+IGJhY2tncm91bmRDb2xvcn07XHJcbmA7XHJcblxyXG5jb25zdCBNZXNzYWdlID0gc3R5bGVkLmRpdmBcclxuICBmb250LXNpemU6IDE0cHg7XHJcbiAgbGluZS1oZWlnaHQ6IDE0cHg7XHJcbiAgY29sb3I6IHJnYmEoMCwgMCwgMCwgMC43KTtcclxuYDtcclxuIl19 */",
  toString: _EMOTION_STRINGIFIED_CSS_ERROR__
});