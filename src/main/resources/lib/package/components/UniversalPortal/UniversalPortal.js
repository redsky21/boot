var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _slicedToArray2 = _interopRequireDefault(require("@babel/runtime/helpers/slicedToArray"));

var _react = _interopRequireDefault(require("react"));

var _reactDom = _interopRequireDefault(require("react-dom"));

function UniversalPortal(_ref) {
  var children = _ref.children;

  var _React$useState = _react["default"].useState(false),
      _React$useState2 = (0, _slicedToArray2["default"])(_React$useState, 2),
      mounted = _React$useState2[0],
      setMounted = _React$useState2[1];

  _react["default"].useEffect(function () {
    setMounted(true);
  }, []);

  return mounted && typeof document !== 'undefined' ? /*#__PURE__*/_reactDom["default"].createPortal(children, document.body) : null;
}

var _default = UniversalPortal;
exports["default"] = _default;