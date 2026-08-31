const themeToggle = document.querySelector('.theme-toggle');
const toggleIcon = document.querySelector('.toggle-icon');
const toggleText = document.querySelector('.toggle-text');
const savedTheme = localStorage.getItem('taskorbit-theme');

function applyTheme(theme) {
  const isLight = theme === 'light';
  document.body.classList.toggle('light-theme', isLight);
  toggleIcon.textContent = isLight ? '☀' : '☾';
  toggleText.textContent = isLight ? 'Light' : 'Dark';
  localStorage.setItem('taskorbit-theme', theme);
}

applyTheme(savedTheme || 'dark');

themeToggle.addEventListener('click', () => {
  const nextTheme = document.body.classList.contains('light-theme') ? 'dark' : 'light';
  applyTheme(nextTheme);
});
