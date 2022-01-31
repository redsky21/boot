import React from 'react';
export declare type TConfirmDialogProps = {
    isOpen: boolean;
    onClose: () => any;
    children: React.ReactNode;
    onConfirm?: (onClose: () => any) => any;
    onCancel?: (onClose: () => any) => any;
    confirmText?: string;
    cancelText?: string;
};
declare function ConfirmDialog({ isOpen, onClose, onConfirm, onCancel, confirmText, cancelText, children, }: TConfirmDialogProps): import("@emotion/react/jsx-runtime").JSX.Element;
export default ConfirmDialog;
