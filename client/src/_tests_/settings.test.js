import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import { Settings } from './Settings';

jest.mock('./FontGridContainer', () => ({
  FontGridContainer: () => <div>FontGridContainer</div>,
}));

jest.mock('./Ruler', () => ({
  Ruler: ({ children }) => <div>Ruler: {children}</div>,
}));

jest.mock('./ReadFocus', () => ({
  ReadFocus: ({ children }) => <div>ReadFocus: {children}</div>,
}));

jest.mock('./TextReader', () => ({
  TextReader: ({ children }) => <div>TextReader: {children}</div>,
}));

jest.mock('../Home/HomePage', () => () => <div>HomePage</div>);

//Accessbility Settings Drawer
describe('Settings Component', () => {
  test('Open the settings drawer', () => {
    render(<Settings />);
    const settingsIcon = screen.getByTestId('SettingsAccessibilityRoundedIcon');
    expect(settingsIcon).toBeInTheDocument();
    fireEvent.click(settingsIcon);
    expect(screen.getByText(/Blindness/i)).toBeInTheDocument();
  });

  //Read Focus Mode Test
  test('toggles Read Focus', () => {
    render(<Settings />);
    fireEvent.click(screen.getByTestId('SettingsAccessibilityRoundedIcon'));
    fireEvent.click(screen.getByText(/Read Focus/i));
    expect(screen.getByText(/ReadFocus/i)).toBeInTheDocument();
    expect(screen.getByText(/HomePage/i)).toBeInTheDocument();
  });

  // Rule mode Test
  test('toggles Ruler', () => {
    render(<Settings />);

    fireEvent.click(screen.getByTestId('SettingsAccessibilityRoundedIcon'));
    fireEvent.click(screen.getByText(/Ruler/i));
    expect(screen.getByText(/Ruler/i)).toBeInTheDocument();
    expect(screen.getByText(/HomePage/i)).toBeInTheDocument();
  });

  //Monochrome Color mode
  test('changes color mode to Monochrome', () => {
    render(<Settings />);

    fireEvent.click(screen.getByTestId('SettingsAccessibilityRoundedIcon'));
    fireEvent.click(screen.getByText(/Monochrome/i));
    expect(screen.getByTestId('root')).toHaveStyle('filter: grayscale(100%)');
  });

  //Low Saturation Color mode
  test('changes color mode to Low Saturation', () => {
    render(<Settings />);

    fireEvent.click(screen.getByTestId('SettingsAccessibilityRoundedIcon'));
    fireEvent.click(screen.getByText(/Low Saturation/i));

    expect(screen.getByTestId('root')).toHaveStyle('filter: saturate(50%)');
  });

  //High Saturation Color mode
  test('changes color mode to High Saturation', () => {
    render(<Settings />);
    fireEvent.click(screen.getByTestId('SettingsAccessibilityRoundedIcon'));
    fireEvent.click(screen.getByText(/High Saturation/i));
    expect(screen.getByTestId('root')).toHaveStyle('filter: saturate(200%)');
  });
});
