var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _styles = require("@mui/material/styles");

var _colors = _interopRequireDefault(require("./colors"));

var theme = (0, _styles.createTheme)({
  typography: {
    htmlFontSize: 10,
    fontFamily: "'Roboto','Noto Sans',sans-serif"
  },
  components: {},
  palette: {
    primary: {
      light: _colors["default"]['primary-300'],
      main: _colors["default"]['primary-500'],
      dark: _colors["default"]['primary-700']
    }
  }
});
var _default = theme;
exports["default"] = _default;