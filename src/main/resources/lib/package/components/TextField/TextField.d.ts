import { TextFieldProps } from '@mui/material/TextField';
declare type SearchProps = {
    isLoading?: boolean;
    onClear?: () => void;
    onSearchClick?: () => void;
};
declare type Props = {
    width?: string | number;
    fullWidth?: boolean;
    labelAlign?: 'left' | 'right';
    labelWidth?: number | string;
    readOnly?: boolean;
    WrapperProps?: Record<string, any>;
    SearchProps?: SearchProps;
};
export declare type TTextFieldProps = Props & Omit<TextFieldProps, keyof Props>;
declare function TextField({ type, label, value, onChange, labelAlign, labelWidth, WrapperProps, required, disabled, readOnly, error, fullWidth, width, SearchProps, sx, ...rest }: TTextFieldProps): import("@emotion/react/jsx-runtime").JSX.Element;
export default TextField;
