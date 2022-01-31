import { TextFieldProps } from '../TextField';
export declare type TTextareaProps = {
    width?: string | number;
    labelAlign?: 'left' | 'right';
    labelWidth?: number | string;
    WrapperProps?: Record<string, any>;
    useResize?: boolean;
} & Omit<TextFieldProps, 'sx'>;
declare function Textarea({ type, value, onChange, WrapperProps, useResize, disabled, width, minRows, maxRows, ...rest }: TTextareaProps): import("@emotion/react/jsx-runtime").JSX.Element;
export default Textarea;
