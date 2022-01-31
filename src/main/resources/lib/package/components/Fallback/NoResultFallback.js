var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.Information = void 0;
exports["default"] = NoResultFallback;

var _extends2 = _interopRequireDefault(require("@babel/runtime/helpers/extends"));

var _objectWithoutProperties2 = _interopRequireDefault(require("@babel/runtime/helpers/objectWithoutProperties"));

var _base = _interopRequireDefault(require("@emotion/styled/base"));

var _react = _interopRequireDefault(require("react"));

var _Fallback = _interopRequireDefault(require("../Fallback"));

var _excluded = ["children", "size"];

function _EMOTION_STRINGIFIED_CSS_ERROR__() { return "You have tried to stringify object returned from `css` function. It isn't supposed to be used directly (e.g. as value of the `className` prop), but rather handed to emotion so it can handle it (e.g. as value of `css` prop)."; }

function NoResultFallback(_ref) {
  var children = _ref.children,
      _ref$size = _ref.size,
      size = _ref$size === void 0 ? 'medium' : _ref$size,
      rest = (0, _objectWithoutProperties2["default"])(_ref, _excluded);
  return /*#__PURE__*/_react["default"].createElement(_Fallback["default"], (0, _extends2["default"])({
    size: size
  }, rest), /*#__PURE__*/_react["default"].createElement(Information, null, "!"), children || /*#__PURE__*/_react["default"].createElement("p", null, "No results found."));
}

var Information = (0, _base["default"])("div", process.env.NODE_ENV === "production" ? {
  target: "e1xj094v0"
} : {
  target: "e1xj094v0",
  label: "Information"
})(process.env.NODE_ENV === "production" ? {
  name: "11vs3fx",
  styles: "display:flex;justify-content:center;align-items:center;margin:0 auto;border:2px solid #b9b9b9;border-radius:50%;width:6rem;height:6rem;color:#b9b9b9;font-weight:400;font-size:4rem;line-height:1;margin-bottom:1rem"
} : {
  name: "11vs3fx",
  styles: "display:flex;justify-content:center;align-items:center;margin:0 auto;border:2px solid #b9b9b9;border-radius:50%;width:6rem;height:6rem;color:#b9b9b9;font-weight:400;font-size:4rem;line-height:1;margin-bottom:1rem",
  map: "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL0ZhbGxiYWNrL05vUmVzdWx0RmFsbGJhY2sudHN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQXVCcUMiLCJmaWxlIjoiLi4vLi4vLi4vc3JjL2NvbXBvbmVudHMvRmFsbGJhY2svTm9SZXN1bHRGYWxsYmFjay50c3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgc3R5bGVkIGZyb20gJ0BlbW90aW9uL3N0eWxlZCc7XHJcbmltcG9ydCBSZWFjdCBmcm9tICdyZWFjdCc7XHJcblxyXG5pbXBvcnQgRmFsbGJhY2sgZnJvbSAnLi4vRmFsbGJhY2snO1xyXG5cclxuZXhwb3J0IHR5cGUgVE5vUmVzdWx0RmFsbGJhY2tQcm9wcyA9IHtcclxuICBzaXplPzogJ3NtYWxsJyB8ICdtZWRpdW0nO1xyXG4gIGNoaWxkcmVuPzogUmVhY3QuUmVhY3ROb2RlO1xyXG59ICYgUmVhY3QuSFRNTEF0dHJpYnV0ZXM8SFRNTERpdkVsZW1lbnQ+O1xyXG5cclxuZXhwb3J0IGRlZmF1bHQgZnVuY3Rpb24gTm9SZXN1bHRGYWxsYmFjayh7XHJcbiAgY2hpbGRyZW4sXHJcbiAgc2l6ZSA9ICdtZWRpdW0nLFxyXG4gIC4uLnJlc3RcclxufTogVE5vUmVzdWx0RmFsbGJhY2tQcm9wcykge1xyXG4gIHJldHVybiAoXHJcbiAgICA8RmFsbGJhY2sgc2l6ZT17c2l6ZX0gey4uLnJlc3R9PlxyXG4gICAgICA8SW5mb3JtYXRpb24+ITwvSW5mb3JtYXRpb24+XHJcbiAgICAgIHtjaGlsZHJlbiB8fCA8cD5ObyByZXN1bHRzIGZvdW5kLjwvcD59XHJcbiAgICA8L0ZhbGxiYWNrPlxyXG4gICk7XHJcbn1cclxuXHJcbmV4cG9ydCBjb25zdCBJbmZvcm1hdGlvbiA9IHN0eWxlZC5kaXZgXHJcbiAgZGlzcGxheTogZmxleDtcclxuICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcclxuICBhbGlnbi1pdGVtczogY2VudGVyO1xyXG4gIG1hcmdpbjogMCBhdXRvO1xyXG4gIGJvcmRlcjogMnB4IHNvbGlkICNiOWI5Yjk7XHJcbiAgYm9yZGVyLXJhZGl1czogNTAlO1xyXG4gIHdpZHRoOiA2cmVtO1xyXG4gIGhlaWdodDogNnJlbTtcclxuICBjb2xvcjogI2I5YjliOTtcclxuICBmb250LXdlaWdodDogNDAwO1xyXG4gIGZvbnQtc2l6ZTogNHJlbTtcclxuICBsaW5lLWhlaWdodDogMTtcclxuICBtYXJnaW4tYm90dG9tOiAxcmVtO1xyXG5gO1xyXG4iXX0= */",
  toString: _EMOTION_STRINGIFIED_CSS_ERROR__
});
exports.Information = Information;