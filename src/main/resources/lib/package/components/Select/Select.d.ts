import { TextFieldProps } from '../TextField';
export declare type TSelectProps = {
    width?: string | number;
    labelAlign?: 'left' | 'right';
    labelWidth?: number | string;
    WrapperProps?: Record<string, any>;
    useNoSelect?: boolean;
} & TextFieldProps;
export default function Select({ children, placeholder, value, width, useNoSelect, WrapperProps, onChange, className, ...rest }: TSelectProps): import("@emotion/react/jsx-runtime").JSX.Element;
