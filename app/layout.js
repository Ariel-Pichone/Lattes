import './globals.css';

export const metadata = {
  title: 'Lattes App',
  description: 'Lattes App',
};

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <body className="md:container md:mx-auto md:mb-5">{children}</body>
    </html>
  );
}
