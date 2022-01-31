import React from 'react';
declare const IconButton: import("@emotion/styled").StyledComponent<{
    variant?: "none" | "outlined" | "contained" | undefined;
    color?: "inherit" | "primary" | "primary-reverse" | "normal" | undefined;
    size?: "inherit" | "xs" | "sm" | "md" | "lg" | "xl" | undefined;
    isLoading?: boolean | undefined;
    disabled?: boolean | undefined;
    fullWidth?: boolean | undefined;
    as?: React.ElementType<any> | undefined;
    to?: string | undefined;
    type?: "button" | "submit" | undefined;
    ButtonBaseProps?: import("@mui/material").ButtonBaseProps<"button", {}> | undefined;
} & React.HTMLAttributes<HTMLDivElement> & React.RefAttributes<HTMLDivElement> & {
    theme?: import("@emotion/react").Theme | undefined;
}, {}, {}>;
export default IconButton;
