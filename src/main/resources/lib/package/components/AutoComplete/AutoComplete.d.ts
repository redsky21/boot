import { AutocompleteProps } from '@mui/material/Autocomplete';
import { TextFieldProps } from '../TextField';
declare type TOption = {
    value: string;
    label: string;
};
declare type Props = {
    label?: string;
    labelAlign?: 'left' | 'right';
    labelWidth?: number | string;
    required?: boolean;
    error?: boolean;
    WrapperProps?: Record<string, any>;
    TextFieldProps?: Omit<TextFieldProps, 'label' | 'labelAlign' | 'labelWidth' | 'required' | 'error'>;
};
export declare type TAutoCompleteProps = Props & Omit<AutocompleteProps<TOption, boolean | undefined, boolean | undefined, boolean | undefined, any>, keyof Props | 'renderInput' | 'renderOption'>;
declare function AutoComplete({ label, labelAlign, labelWidth, required, error, value, onChange, WrapperProps, TextFieldProps, className, ...rest }: TAutoCompleteProps): import("@emotion/react/jsx-runtime").JSX.Element;
export default AutoComplete;
