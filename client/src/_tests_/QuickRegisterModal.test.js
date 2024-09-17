/**
 * @jest-environment jsdom
 */

import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';
import { QuickRegisterModal } from '../Home/Components/QuickRegisterModal';
describe('QuickRegisterModal', () => {
    const setup = (props = {}) => {
        const utils = render(<QuickRegisterModal open={true} handleClose={() => {}} {...props} />);
        const firstNameInput = screen.getByLabelText(/first name/i);
        const lastNameInput = screen.getByLabelText(/last name/i);
        const emailInput = screen.getByLabelText(/email address/i);
        const phoneInput = screen.getByLabelText(/phone number/i);
        const submitButton = screen.getByText(/sign up/i);
        return {
            ...utils,
            firstNameInput,
            lastNameInput,
            emailInput,
            phoneInput,
            submitButton,
        };
    };

    test('renders the modal', () => {
        setup();

        expect(screen.getByText(/quick register -/i)).toBeInTheDocument();
        expect(screen.getByLabelText(/first name/i)).toBeInTheDocument();
        expect(screen.getByLabelText(/last name/i)).toBeInTheDocument();
        expect(screen.getByLabelText(/email address/i)).toBeInTheDocument();
        expect(screen.getByLabelText(/phone number/i)).toBeInTheDocument();
        expect(screen.getByText(/sign up/i)).toBeInTheDocument();
    });

    test('handles form input changes', () => {
        const { firstNameInput, lastNameInput, emailInput, phoneInput } = setup();

        fireEvent.change(firstNameInput, { target: { value: 'John' } });
        fireEvent.change(lastNameInput, { target: { value: 'Doe' } });
        fireEvent.change(emailInput, { target: { value: 'john.doe@example.com' } });
        fireEvent.change(phoneInput, { target: { value: '1234567890' } });

        expect(firstNameInput.value).toBe('John');
        expect(lastNameInput.value).toBe('Doe');
        expect(emailInput.value).toBe('john.doe@example.com');
        expect(phoneInput.value).toBe('1234567890');
    });

    test('closes the modal when clicking outside of it', () => {
        const { container } = setup();
        const modal = container.querySelector('.MuiModal-root');
        fireEvent.mouseDown(modal);

        expect(screen.queryByText(/quick register -/i)).not.toBeInTheDocument();
    });
});