var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.TabContext = void 0;
exports.useTabContext = useTabContext;

var _react = _interopRequireDefault(require("react"));

var TabContext = /*#__PURE__*/_react["default"].createContext(undefined);

exports.TabContext = TabContext;

function useTabContext() {
  return _react["default"].useContext(TabContext);
}