import './globals.css';
import Link from 'next/link';

export const metadata = {
  title: 'Lattes App',
  description: 'Lattes App',
};

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      {/* <div>
        <h2>
          <Link href="/instituto">Instituto</Link>
          <Link href="/pesquisador">Pesquisador</Link>
        </h2>
      </div> */}
      <body className="md:container md:mx-auto md:mb-5">{children}</body>
    </html>
  );
}
